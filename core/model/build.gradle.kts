plugins {
    id("awesomesunsetwallpapers.multiplatform.library.core")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:network"))
        }
    }
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.core.model"
}