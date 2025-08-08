package br.com.parreira.kmpcrudsimples.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.parreira.kmpcrudsimples.domain.model.Post
import br.com.parreira.kmpcrudsimples.presentation.PostViewModel
import org.koin.compose.koinInject // CORREÇÃO: Usamos koinInject para singletons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(viewModel: PostViewModel = koinInject()) { // CORREÇÃO: Mudança aqui
    val uiState by viewModel.uiState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var editingPost by remember { mutableStateOf<Post?>(null) }

    Scaffold(
        topBar = { TopAppBar(title = { Text("KMP CRUD Posts") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                editingPost = null
                showDialog = true
            }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Post")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            uiState.error?.let { error ->
                Text(
                    text = "Erro: $error",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.posts, key = { it.id }) { post ->
                    PostItem(
                        post = post,
                        onEditClick = {
                            editingPost = post
                            showDialog = true
                        },
                        onDeleteClick = { viewModel.deletePost(post.id) }
                    )
                }
            }

            if (showDialog) {
                PostDialog(
                    post = editingPost,
                    onDismiss = { showDialog = false },
                    onConfirm = { title, body ->
                        showDialog = false
                        if (editingPost == null) {
                            viewModel.createPost(title, body)
                        } else {
                            viewModel.updatePost(editingPost!!.copy(title = title, body = body))
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun PostItem(post: Post, onEditClick: () -> Unit, onDeleteClick: () -> Unit) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = post.title, style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = post.body, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = onEditClick) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(Icons.Default.Delete, contentDescription = "Deletar")
                }
            }
        }
    }
}

@Composable
fun PostDialog(
    post: Post?,
    onDismiss: () -> Unit,
    onConfirm: (title: String, body: String) -> Unit
) {
    var title by remember { mutableStateOf(post?.title ?: "") }
    var body by remember { mutableStateOf(post?.body ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (post == null) "Novo Post" else "Editar Post") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Título") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = body,
                    onValueChange = { body = it },
                    label = { Text("Conteúdo") }
                )
            }
        },
        confirmButton = {
            Button(onClick = { onConfirm(title, body) }) {
                Text("Salvar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
