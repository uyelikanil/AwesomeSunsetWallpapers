plugins {
    id("awesomesunsetwallpapers.multiplatform.core.library")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:network"))
            implementation(project(":core:model"))

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            api(libs.ktor.client.mock)
            api(libs.androidx.test.runner)
            api(libs.kotlinx.coroutines.test)
            api(libs.junit4)
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
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.core.testing"
}