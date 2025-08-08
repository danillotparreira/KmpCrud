package br.com.parreira.kmpcrudsimples.presentation

import androidx.compose.runtime.Immutable
import br.com.parreira.kmpcrudsimples.domain.model.Post
import br.com.parreira.kmpcrudsimples.domain.usecase.post.CreatePostUseCase
import br.com.parreira.kmpcrudsimples.domain.usecase.post.DeletePostUseCase
import br.com.parreira.kmpcrudsimples.domain.usecase.post.GetPostsUseCase
import br.com.parreira.kmpcrudsimples.domain.usecase.post.UpdatePostUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Immutable
data class PostUiState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class PostViewModel(
    private val getPostsUseCase: GetPostsUseCase,
    private val createPostUseCase: CreatePostUseCase,
    private val updatePostUseCase: UpdatePostUseCase,
    private val deletePostUseCase: DeletePostUseCase
) {
    private val viewModelScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _uiState = MutableStateFlow(PostUiState())
    val uiState: StateFlow<PostUiState> = _uiState

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            getPostsUseCase()
                .onSuccess { posts ->
                    _uiState.update { it.copy(posts = posts, isLoading = false) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(error = e.message, isLoading = false) }
                }
        }
    }

    fun createPost(title: String, body: String) {
        viewModelScope.launch {
            createPostUseCase(title, body)
            loadPosts()
        }
    }

    fun updatePost(post: Post) {
        viewModelScope.launch {
            updatePostUseCase(post).onSuccess {
                loadPosts()
            }.onFailure { e ->
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    fun deletePost(id: Int) {
        viewModelScope.launch {
            deletePostUseCase(id).onSuccess {
                loadPosts()
            }.onFailure { e ->
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }
}