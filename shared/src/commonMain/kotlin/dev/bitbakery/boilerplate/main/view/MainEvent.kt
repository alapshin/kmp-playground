package dev.bitbakery.boilerplate.main.view

sealed interface MainEvent {
    data object Logout : MainEvent
}
