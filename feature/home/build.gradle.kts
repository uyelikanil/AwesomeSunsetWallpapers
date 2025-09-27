plugins {
    id("awesomesunsetwallpapers.android.feature")
    id("awesomesunsetwallpapers.multiplatform.library.compose")
    id("awesomesunsetwallpapers.android.feature.koin")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.feature.home"
}

dependencies {
    implementation(project(":core:resource"))
    implementation(compose.components.resources)
}