plugins {
    id("awesomesunsetwallpapers.android.feature")
    id("awesomesunsetwallpapers.multiplatform.library.compose")
    id("awesomesunsetwallpapers.android.feature.koin")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail"
}

dependencies {
    implementation(project(":core:resource"))
    testImplementation(project(":core:data"))
    testImplementation(project(":core:testing"))
    implementation(libs.androidx.activity.compose)
    implementation(compose.components.resources)
}