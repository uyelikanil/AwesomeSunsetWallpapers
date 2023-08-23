plugins {
    kotlin("kapt")
    id("awesomesunsetwallpapers.android.feature")
    id("awesomesunsetwallpapers.compose")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail"
}

dependencies {
    testImplementation(project(":core:data"))
    testImplementation(project(":core:testing"))

    implementation(libs.androidx.activity.compose)
}