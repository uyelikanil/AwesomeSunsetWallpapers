plugins {
    kotlin("kapt")
    id("awesomesunsetwallpapers.android.feature")
    id("awesomesunsetwallpapers.compose")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.feature.home"
}

dependencies {
    implementation(project(":feature:main"))

    implementation(libs.androidx.paging.compose)
}