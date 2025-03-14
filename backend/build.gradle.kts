plugins {
    application
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktor)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.kotlin.serialization)

    id("dev.bitbakery.gradle.detekt")
    id("dev.bitbakery.gradle.ktlint")
    id("dev.bitbakery.gradle.kotlin.jvm")
}

group = "dev.bitbakery.boilerplate"
version = "1.0.0"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("dev.bitbakery.boileplate.database")
        }
    }
}

dependencies {
    implementation(libs.logback)
    implementation(libs.kotlinx.datetime)
    implementation(libs.bundles.ktor.server)

    ksp(libs.kotlininject.compiler)
    ksp(libs.kotlininject.anvil.compiler)
    implementation(libs.kotlininject.runtime)
    implementation(libs.kotlininject.anvil.runtime)
    implementation(libs.kotlininject.anvil.runtime.optional)

    implementation(libs.sqldelight.jvm)
    implementation(libs.swagger.codegen)
}
