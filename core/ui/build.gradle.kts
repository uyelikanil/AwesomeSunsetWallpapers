plugins {
    id("awesomesunsetwallpapers.android.library")
    id("awesomesunsetwallpapers.library.compose")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.core.ui"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:model"))

    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
}