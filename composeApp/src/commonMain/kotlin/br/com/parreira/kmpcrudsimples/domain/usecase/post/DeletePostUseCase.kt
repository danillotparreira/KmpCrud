package br.com.parreira.kmpcrudsimples.domain.usecase.post

import br.com.parreira.kmpcrudsimples.domain.repository.PostRepository
class DeletePostUseCase(private val repository: PostRepository) {
    suspend operator fun invoke(id: Int) = repository.deletePost(id)
}
