plugins {
    id("awesomesunsetwallpapers.android.feature")
    id("awesomesunsetwallpapers.library.compose")
    id("awesomesunsetwallpapers.android.feature.koin")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.feature.home"
}

dependencies {
    implementation(project(":feature:main"))

    implementation(libs.androidx.paging.compose)
}