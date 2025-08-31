import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("awesomesunsetwallpapers.multiplatform.core.library")
    id("awesomesunsetwallpapers.multiplatform.koin")
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

    buildTypes {
        val pexelsAuthKey: String = gradleLocalProperties(rootDir,
            providers).getProperty("pexels.auth.key")

        release {
            buildConfigField("String", "PEXELS_AUTH_KEY", pexelsAuthKey)
        }
        debug {
            buildConfigField("String", "PEXELS_AUTH_KEY", pexelsAuthKey)
        }
    }

    buildFeatures {
        buildConfig = true
    }
}