import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("awesomesunsetwallpapers.android.library")
    id("awesomesunsetwallpapers.android.koin")
    alias(libs.plugins.kotlin.serialization)
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

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor)
    implementation(libs.ktor.okhttp)
    implementation(libs.ktor.content.negotiation)
    implementation(libs.ktor.kotlin.serialization)
    implementation(libs.ktor.logging)
    implementation(libs.androidx.paging.compose)
}