package br.com.parreira.kmpcrud.core.di

import br.com.parreira.kmpcrud.core.remote.ApiService
import br.com.parreira.kmpcrud.features.post.data.repository.PostRepositoryImpl
import br.com.parreira.kmpcrud.features.post.data.repository.PostRepository
import br.com.parreira.kmpcrud.features.post.data.remote.PostService
import br.com.parreira.kmpcrud.features.post.interactor.PostInteractorImpl
import br.com.parreira.kmpcrud.features.post.presenter.PostPresenter
import br.com.parreira.kmpcrud.features.post.router.PostRouter
import br.com.parreira.kmpcrudsimples.features.post.interactor.PostInteractor
import org.koin.dsl.module

//val appModule = module {
//    single { ApiService() }
//    single { PostService(get()) }
//    single<PostRepository> { PostRepositoryImpl(get()) }
//
//    single<PostInteractor> { PostInteractorImpl(get()) }
//    single { PostRouter() }
//    single { PostPresenter(get()) }
//}

val appModule = module {
    single { ApiService() }
}