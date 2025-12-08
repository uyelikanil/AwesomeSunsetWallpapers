plugins {
    id("awesomesunsetwallpapers.multiplatform.feature")
    id("awesomesunsetwallpapers.multiplatform.library.compose")
    id("awesomesunsetwallpapers.multiplatform.library.koin")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:resource"))
            implementation(compose.components.resources)
        }
    }
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.feature.home"
}