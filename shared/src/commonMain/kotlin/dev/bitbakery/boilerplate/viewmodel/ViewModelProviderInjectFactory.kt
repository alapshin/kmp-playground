package dev.bitbakery.boilerplate.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import me.tatarka.inject.annotations.Inject
import kotlin.reflect.KClass

val CREATION_CALLBACK_KEY = object : CreationExtras.Key<(Any) -> ViewModel> {}

/**
 * [ViewModelProvider.Factory] that uses ViewModel factories generated by kotlin-inject.
 *
 * Supports both ViewModels that have no assisted dependencies (ie. everything provided by the DI graph)
 * as well as ViewModels that require them, including ViewModels that require [SavedStateHandle].
 */
@Inject
class ViewModelProviderInjectFactory(
    /**
     * Map of simple ViewModel factories for ViewModels that have no assisted dependencies.
     */
    private val viewModelMap: Map<KClass<out ViewModel>, () -> ViewModel> = emptyMap(),

    /**
     * Map of ViewModel factories that just require a [SavedStateHandle].
     */
    private val savedStateViewModelMap: Map<KClass<out ViewModel>, (SavedStateHandle) -> ViewModel> = emptyMap(),

    /**
     * Map of advanced ViewModel factories that require one or more assisted dependencies outside of [SavedStateHandle].
     */
    private val viewModelFactoryMap: Map<KClass<out ViewModel>, Any> = emptyMap(),
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        return viewModelMap[modelClass]?.invoke() as T?
            ?: savedStateViewModelMap[modelClass]?.invoke(extras.createSavedStateHandle()) as? T
            ?: run {
                // The callback is meant to use an existing factory and provide the remaining
                // assisted dependencies to complete the creation of the ViewModel.
                val callback = extras[CREATION_CALLBACK_KEY] ?: return@run null
                val viewModelFactory = viewModelFactoryMap[modelClass] ?: return@run null
                if (viewModelFactory is SavedStateViewModelFactory) {
                    // If the view model factory requires a saved state handle, we need to create it
                    viewModelFactory.savedStateHandle = extras.createSavedStateHandle()
                }
                callback(viewModelFactory) as? T
            }
            ?: error("No view model found for $modelClass")
    }
}
