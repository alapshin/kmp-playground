package dev.bitbakery.boilerplate.post.inject

import de.jensklingenberg.ktorfit.Ktorfit
import dev.bitbakery.boilerplate.base.ViewModelClass
import dev.bitbakery.boilerplate.base.ViewModelInitializer
import dev.bitbakery.boilerplate.post.data.PostApi
import dev.bitbakery.boilerplate.post.data.PostListFactory
import dev.bitbakery.boilerplate.post.data.createPostApi
import dev.bitbakery.boilerplate.post.ui.PostListPresenter
import dev.bitbakery.boilerplate.post.ui.PostListPresenterImpl
import dev.bitbakery.boilerplate.post.ui.PostListViewModel
import me.tatarka.inject.annotations.IntoMap
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@ContributesTo(AppScope::class)
interface PostComponent {
    @Provides
    @SingleIn(AppScope::class)
    fun provideApiService(ktorfit: Ktorfit): PostApi = ktorfit.createPostApi()

    @Provides
    @SingleIn(AppScope::class)
    fun providePostListStore(factory: PostListFactory) = factory.create()

    @Provides
    fun PostListPresenterImpl.bind(): PostListPresenter = this

    @IntoMap
    @Provides
    fun provideAuthorListViewModel(presenter: PostListPresenter): Pair<ViewModelClass, ViewModelInitializer> =
        PostListViewModel::class to { PostListViewModel(presenter) }
}
