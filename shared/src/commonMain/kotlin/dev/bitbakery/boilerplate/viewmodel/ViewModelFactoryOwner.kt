package dev.bitbakery.boilerplate.viewmodel

import androidx.lifecycle.ViewModelProvider

interface ViewModelFactoryOwner {
    val viewModelFactory: ViewModelProvider.Factory
}
