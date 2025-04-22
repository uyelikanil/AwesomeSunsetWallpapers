plugins {id("awesomesunsetwallpapers.android.library")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.core.testing"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:model"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.paging.compose)
    implementation(libs.ktor)
    implementation(libs.ktor.okhttp)
    implementation(libs.ktor.content.negotiation)
    implementation(libs.ktor.kotlin.serialization)
    api(libs.ktor.mock)
    api(libs.androidx.test.runner)
    api(libs.kotlinx.coroutines.test)
    api(libs.androidx.paging.test)
    api(libs.junit4)
}