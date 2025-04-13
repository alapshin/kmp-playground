package dev.bitbakery.boilerplate.post.inject

import androidx.lifecycle.ViewModel
import de.jensklingenberg.ktorfit.Ktorfit
import dev.bitbakery.boilerplate.post.data.PostApi
import dev.bitbakery.boilerplate.post.data.PostDetailStore
import dev.bitbakery.boilerplate.post.data.PostDetailStoreFactory
import dev.bitbakery.boilerplate.post.data.PostListStore
import dev.bitbakery.boilerplate.post.data.PostListStoreFactory
import dev.bitbakery.boilerplate.post.data.PostRepository
import dev.bitbakery.boilerplate.post.data.createPostApi
import dev.bitbakery.boilerplate.post.ui.PostDetailViewModel
import dev.bitbakery.boilerplate.post.ui.PostListViewModel
import dev.bitbakery.boilerplate.viewmodel.ViewModelPair
import me.tatarka.inject.annotations.IntoMap
import me.tatarka.inject.annotations.Provides
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn
import kotlin.reflect.KClass

@ContributesTo(AppScope::class)
interface PostComponent {
    @Provides
    @SingleIn(AppScope::class)
    fun provideApiService(ktorfit: Ktorfit): PostApi = ktorfit.createPostApi()

    @Provides
    @SingleIn(AppScope::class)
    fun providePostListStore(factory: PostListStoreFactory): PostListStore = factory.create()

    @Provides
    @SingleIn(AppScope::class)
    fun providePostDetailStore(factory: PostDetailStoreFactory): PostDetailStore = factory.create()

    @IntoMap
    @Provides
    fun providePostListViewModel(repository: PostRepository): ViewModelPair =
        PostListViewModel::class to { PostListViewModel(repository) }

    @IntoMap
    @Provides
    fun providePostDetailViewModel(factory: (postId: Long) -> PostDetailViewModel): Pair<KClass<out ViewModel>, Any> =
        PostDetailViewModel::class to PostDetailViewModel.Factory(factory)
}
