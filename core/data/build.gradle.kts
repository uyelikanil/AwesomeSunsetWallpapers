plugins {
    kotlin("kapt")
    id("awesomesunsetwallpapers.android.library")
    id("awesomesunsetwallpapers.android.hilt")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.core.data"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:common"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.paging.compose)
}