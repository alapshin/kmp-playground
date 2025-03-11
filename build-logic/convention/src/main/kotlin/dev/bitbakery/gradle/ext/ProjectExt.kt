package dev.bitbakery.gradle.ext

import com.android.build.api.variant.AndroidComponentsExtension
import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinBaseExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

internal fun Project.android(action: BaseExtension.() -> Unit) = extensions.configure<BaseExtension>(action)

@Suppress("UnusedPrivateMember")
internal fun Project.androidComponents(action: AndroidComponentsExtension<*, *, *>.() -> Unit) {
    extensions.configure(AndroidComponentsExtension::class.java, action)
}

internal fun Project.kotlin(action: KotlinBaseExtension.() -> Unit) = extensions.configure<KotlinBaseExtension>(action)
internal fun Project.kotlinJvm(action: KotlinJvmExtension.() -> Unit) = extensions.configure<KotlinJvmExtension>(action)
internal fun Project.kotlinAndroid(action: KotlinAndroidExtension.() -> Unit) = extensions.configure<KotlinAndroidExtension>(action)
internal fun Project.kotlinMultiplatform(action: KotlinMultiplatformExtension.() -> Unit) = extensions.configure<KotlinMultiplatformExtension>(action)
