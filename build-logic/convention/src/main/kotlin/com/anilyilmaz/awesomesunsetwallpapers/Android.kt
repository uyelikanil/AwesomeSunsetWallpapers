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
            sourceCompatibility = JavaVersion.VERSION_19
            targetCompatibility = JavaVersion.VERSION_19
        }
    }

    // ---- Kotlin bits (safe across KMP/Android/JVM)
    configureKotlinSafely()
}

private fun Project.configureKotlinSafely() {
    val warningsAsErrors = providers
        .gradleProperty("warningsAsErrors")
        .map(String::toBoolean)
        .orElse(false)

    extensions.findByType(KotlinAndroidProjectExtension::class.java)?.let { ext ->
        ext.compilerOptions {
            jvmTarget.set(JvmTarget.JVM_19)
            allWarningsAsErrors.set(warningsAsErrors)
        }
    }

    extensions.findByType(KotlinJvmProjectExtension::class.java)?.let { ext ->
        ext.compilerOptions {
            jvmTarget.set(JvmTarget.JVM_19)
            allWarningsAsErrors.set(warningsAsErrors)
        }
    }

    extensions.findByType(KotlinMultiplatformExtension::class.java)?.let { kmp ->
        kmp.compilerOptions {
            allWarningsAsErrors.set(warningsAsErrors)
        }

        kmp.targets.withType(KotlinAndroidTarget::class.java).configureEach {
            compilerOptions { jvmTarget.set(JvmTarget.JVM_19) }
        }
        kmp.targets.withType(KotlinJvmTarget::class.java).configureEach {
            compilerOptions { jvmTarget.set(JvmTarget.JVM_19) }
        }
    }
}
