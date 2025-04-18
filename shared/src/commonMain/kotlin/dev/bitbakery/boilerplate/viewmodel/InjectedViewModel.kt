
@file:Suppress(
    "CompositionLocalAllowlist",
    "ktlint:compose:compositionlocal-allowlist",
)

package dev.bitbakery.boilerplate.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import me.tatarka.inject.annotations.Assisted

val LocalViewModelFactoryOwner =
    staticCompositionLocalOf<ViewModelFactoryOwner> {
        error("No ViewModelFactoryOwner was provided provided via LocalViewModelFactoryOwner")
    }

/**
 * Returns or creates a [ViewModel], scoped to the local
 * [ViewModelStoreOwner]. This can be a navigation backstack entry, a fragment, or an activity.
 *
 * Requires a [LocalViewModelFactoryOwner] to be set in the composition tree.
 *
 * @see [viewModel]
 */
@Composable
inline fun <reified VM : ViewModel> injectedViewModel(
    viewModelStoreOwner: ViewModelStoreOwner =
        checkNotNull(LocalViewModelStoreOwner.current) {
            "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
        },
    key: String? = null,
    factory: ViewModelProvider.Factory? = LocalViewModelFactoryOwner.current.viewModelFactory,
): VM =
    viewModel<VM>(
        viewModelStoreOwner = viewModelStoreOwner,
        key = key,
        factory = factory,
    )

/**
 * Returns or creates a [ViewModel] annotated with [ContributesViewModel], scoped to the local
 * [ViewModelStoreOwner]. This can be a navigation backstack entry, a fragment, or an activity.
 *
 * This overload takes in a ViewModel factory type that can be used to create ViewModels with
 * assisted parameters (outside of `SavedStateHandle`, which doesn't need a factory). ViewModel
 * factories are automatically created by kotlin-inject for ViewModels with [Assisted]-annotated
 * parameters that have a type other than `SavedStateHandle`. They factories are named `{ViewModel}Factory`.
 *
 * Requires a [LocalViewModelFactoryOwner] to be set in the composition tree.
 *
 * @see [viewModel]
 */
@Composable
inline fun <reified VM : ViewModel, reified VMF> injectedViewModel(
    viewModelStoreOwner: ViewModelStoreOwner =
        checkNotNull(LocalViewModelStoreOwner.current) {
            "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
        },
    key: String? = null,
    factory: ViewModelProvider.Factory? = LocalViewModelFactoryOwner.current.viewModelFactory,
    noinline creationCallback: (VMF) -> VM,
): VM =
    viewModel<VM>(
        viewModelStoreOwner = viewModelStoreOwner,
        key = key,
        factory = factory,
        extras =
            viewModelStoreOwner.run {
                if (this is HasDefaultViewModelProviderFactory) {
                    this.defaultViewModelCreationExtras.withCreationCallback(creationCallback)
                } else {
                    CreationExtras.Empty.withCreationCallback(creationCallback)
                }
            },
    )
