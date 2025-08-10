package br.com.parreira.kmpcrud.features.post.presenter

import androidx.compose.runtime.Immutable
import br.com.parreira.kmpcrud.features.post.entity.Post
import br.com.parreira.kmpcrudsimples.features.post.interactor.PostInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


@Immutable
data class PostUiState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class PostPresenter(
    private val postInteractor: PostInteractor
) {
    private val presenterScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _uiState = MutableStateFlow(PostUiState())
    val uiState = _uiState.asStateFlow()

    fun loadPosts() {
        presenterScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            postInteractor.getAll()
                .onSuccess { posts ->
                    _uiState.update { it.copy(posts = posts.reversed(), isLoading = false) }
                }
                .onFailure { e ->
                    _uiState.update { it.copy(error = e.message, isLoading = false) }
                }
        }
    }

    fun create(title: String, body: String) {
        presenterScope.launch {
            postInteractor.create(title, body).onSuccess {
                loadPosts()
            }.onFailure { e ->
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    fun update(post: Post) {
        presenterScope.launch {
            postInteractor.update(post).onSuccess {
                loadPosts()
            }.onFailure { e ->
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }

    fun delete(id: Int) {
        presenterScope.launch {
            postInteractor.delete(id).onSuccess {
                loadPosts()
            }.onFailure { e ->
                _uiState.update { it.copy(error = e.message) }
            }
        }
    }
}