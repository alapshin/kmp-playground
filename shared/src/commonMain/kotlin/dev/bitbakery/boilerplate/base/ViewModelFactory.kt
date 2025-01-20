@file:Suppress(
    "CompositionLocalAllowlist",
    "ktlint:compose:compositionlocal-allowlist",
)

package dev.bitbakery.boilerplate.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import me.tatarka.inject.annotations.Inject
import kotlin.reflect.KClass

typealias ViewModelClass = KClass<out ViewModel>
typealias ViewModelInitializer = () -> ViewModel

/**
 * Factory that provides view models vie dependency injection
 * @property entries mapping between [ViewModel] class and its initialization lambda
 */
@Inject
class ViewModelFactory(
    val entries: Map<ViewModelClass, ViewModelInitializer>,
) {
    inline fun <reified VM : ViewModel> create(): VM =
        checkNotNull(entries[VM::class]) {
            "Factory method for ${VM::class.qualifiedName} is missing"
        }.invoke() as VM

    @Composable
    inline fun <reified VM : ViewModel> request(): VM =
        viewModel {
            this@ViewModelFactory.create()
        }
}

/**
 * The CompositionLocal containing the current [ViewModelFactory].
 */

val LocalViewModelFactory = compositionLocalOf { ViewModelFactory(emptyMap()) }

@Composable
inline fun <reified VM : ViewModel> injectViewModel() = LocalViewModelFactory.current.request<VM>()
