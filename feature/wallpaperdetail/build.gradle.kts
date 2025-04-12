plugins {
    id("awesomesunsetwallpapers.android.feature")
    id("awesomesunsetwallpapers.library.compose")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail"
}

dependencies {
    testImplementation(project(":core:data"))
    testImplementation(project(":core:testing"))

    implementation(libs.androidx.activity.compose)
}