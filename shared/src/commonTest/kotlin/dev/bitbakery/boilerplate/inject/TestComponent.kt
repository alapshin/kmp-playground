package dev.bitbakery.boilerplate.inject

import dev.bitbakery.boilerplate.auth.data.LoginManager
import dev.bitbakery.boilerplate.network.TokenStorage
import io.ktor.client.engine.HttpClientEngine
import me.tatarka.inject.annotations.Provides

abstract class TestComponent(
    @get:Provides protected val engine: HttpClientEngine,
) {
    abstract val loginManager: LoginManager
    abstract val tokenStorage: TokenStorage
}

// Workaround for inability to use generated test component directly.
// In a current KSP setup component code is generated for specific platform (android and desktop) and code from
// commonTest doesn't have access to it.
// To work around this each platform-specific test source set has its own component and actual implementation of this
// method.
expect fun createTestComponent(engine: HttpClientEngine): TestComponent
