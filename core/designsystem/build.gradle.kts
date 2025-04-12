plugins {
    id("awesomesunsetwallpapers.android.library")
    id("awesomesunsetwallpapers.library.compose")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.core.designsystem"
}

dependencies {
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
}