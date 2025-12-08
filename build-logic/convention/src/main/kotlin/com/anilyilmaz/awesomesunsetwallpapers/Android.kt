package com.anilyilmaz.awesomesunsetwallpapers

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinAndroidTarget
import org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget

internal fun Project.configureAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = 36
        defaultConfig { minSdk = 23 }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_21
            targetCompatibility = JavaVersion.VERSION_21
        }
    }

    configureKotlinSafely()
}

private fun Project.configureKotlinSafely() {
    val toolchainVersion = (project.findProperty("kotlin.jvmToolchain") as? String)?.toInt() ?: 21
    val defaultJvmTarget = (project.findProperty("kotlin.jvmTarget") as? String)
        ?.let { JvmTarget.fromTarget(it) } ?: JvmTarget.JVM_21

    project.plugins.withId("org.jetbrains.kotlin.android") {
        project.extensions.configure(KotlinAndroidProjectExtension::class.java) {
            jvmToolchain(toolchainVersion)
            compilerOptions {
                jvmTarget.set(defaultJvmTarget)
            }
        }
    }

    project.plugins.withId("org.jetbrains.kotlin.jvm") {
        project.extensions.configure(KotlinJvmProjectExtension::class.java) {
            jvmToolchain(toolchainVersion)
            compilerOptions {
                jvmTarget.set(defaultJvmTarget)
            }
        }
    }

    project.plugins.withId("org.jetbrains.kotlin.multiplatform") {
        project.extensions.configure(KotlinMultiplatformExtension::class.java) {
            jvmToolchain(toolchainVersion)

            // Set jvmTarget on relevant targets (androidTarget(), jvm())
            targets.withType(KotlinAndroidTarget::class.java).configureEach {
                compilerOptions { jvmTarget.set(defaultJvmTarget) }
            }
            targets.withType(KotlinJvmTarget::class.java).configureEach {
                compilerOptions { jvmTarget.set(defaultJvmTarget) }
            }
        }
    }
}
