plugins {
    kotlin("kapt")
    id("awesomesunsetwallpapers.android.library")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.core.model"
}

dependencies {
    implementation(project(":core:network"))
}