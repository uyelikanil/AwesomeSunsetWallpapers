plugins {
    id("awesomesunsetwallpapers.android.feature")
    id("awesomesunsetwallpapers.library.compose")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.feature.home"
}

dependencies {
    implementation(project(":feature:main"))

    implementation(libs.androidx.paging.compose)
}