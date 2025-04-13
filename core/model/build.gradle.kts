plugins {
    id("awesomesunsetwallpapers.android.library")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.core.model"
}

dependencies {
    implementation(project(":core:network"))
    implementation(libs.androidx.compose.runtime)
}