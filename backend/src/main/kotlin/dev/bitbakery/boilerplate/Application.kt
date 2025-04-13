package dev.bitbakery.boilerplate

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.netty.EngineMain
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.openapi.openAPI
import io.ktor.server.resources.Resources
import io.ktor.server.routing.routing

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    val component = ApplicationComponent::class.create(this)
    configureRouting(component)
    configureSerialization()
}

fun Application.configureRouting(component: ApplicationComponent) {
    install(Resources)
    component.postRouting.configure(this)
    routing {
        openAPI(
            path = "openapi",
            swaggerFile = "openapi/documentation.yaml",
        )
    }
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}
