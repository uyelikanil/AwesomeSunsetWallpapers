import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("awesomesunsetwallpapers.multiplatform.library.core")
    id("awesomesunsetwallpapers.multiplatform.library.koin")
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.core.network"

    val pexelsAuthKey: String = (gradleLocalProperties(rootDir, providers)
        .getProperty("pexels.auth.key")
        ?: "YOUR_PEXELS_API_KEY"
            ).also {
            if (it == "YOUR_PEXELS_API_KEY") {
                logger.lifecycle(
                    "⚠️ No 'pexels_auth_key' found in local.properties. " +
                            "Using a dummy key; API calls will fail until you set a real one."
                )
            }
        }

    defaultConfig {
        buildConfigField("String", "PEXELS_AUTH_KEY", "\"$pexelsAuthKey\"")
    }

    buildFeatures {
        buildConfig = true
    }
}