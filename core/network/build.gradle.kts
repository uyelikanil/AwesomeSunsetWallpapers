plugins {
    kotlin("kapt")
    id("awesomesunsetwallpapers.android.library")
    id("awesomesunsetwallpapers.android.hilt")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.core.network"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.androidx.paging.compose)
}