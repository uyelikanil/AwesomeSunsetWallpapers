plugins {
    id("awesomesunsetwallpapers.multiplatform.library.core")
    id("awesomesunsetwallpapers.multiplatform.library.koin")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:model"))
        }
    }
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.core.system"
}