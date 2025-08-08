package br.com.parreira.kmpcrudsimples.di

import br.com.parreira.kmpcrudsimples.data.remote.ApiService
import br.com.parreira.kmpcrudsimples.data.repository.PostRepositoryImpl
import br.com.parreira.kmpcrudsimples.domain.repository.PostRepository
import br.com.parreira.kmpcrudsimples.domain.usecase.post.CreatePostUseCase
import br.com.parreira.kmpcrudsimples.domain.usecase.post.DeletePostUseCase
import br.com.parreira.kmpcrudsimples.domain.usecase.post.GetPostsUseCase
import br.com.parreira.kmpcrudsimples.domain.usecase.post.UpdatePostUseCase
import br.com.parreira.kmpcrudsimples.presentation.PostViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val appModule = module {
    single { ApiService() }
    single<PostRepository> { PostRepositoryImpl(get()) }

    factoryOf(::GetPostsUseCase)
    factoryOf(::CreatePostUseCase)
    factoryOf(::UpdatePostUseCase)
    factoryOf(::DeletePostUseCase)

    single {
        PostViewModel(
            getPostsUseCase = get(),
            createPostUseCase = get(),
            updatePostUseCase = get(),
            deletePostUseCase = get()
        )
    }
}