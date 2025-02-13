package dev.bitbakery.boilerplate.arch

import androidx.lifecycle.ViewModel
import kotlin.reflect.KClass

typealias ViewModelPair = Pair<KClass<out ViewModel>, () -> ViewModel>
