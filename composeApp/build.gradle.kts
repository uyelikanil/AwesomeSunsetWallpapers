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
            implementation(project(":core:model"))
            implementation(project(":feature:main"))
            implementation(project(":feature:home"))
            implementation(project(":feature:wallpaperdetail"))
        }
    }
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.composeApp"
}
