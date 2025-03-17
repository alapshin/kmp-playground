package dev.bitbakery.boilerplate.viewmodel

import androidx.lifecycle.ViewModel
import kotlin.reflect.KClass

typealias ViewModelPair = Pair<KClass<out ViewModel>, () -> ViewModel>
