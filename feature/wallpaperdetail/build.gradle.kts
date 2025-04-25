plugins {
    id("awesomesunsetwallpapers.android.feature")
    id("awesomesunsetwallpapers.library.compose")
    id("awesomesunsetwallpapers.android.feature.koin")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail"
}

dependencies {
    testImplementation(project(":core:data"))
    testImplementation(project(":core:testing"))

    implementation(libs.androidx.activity.compose)
}