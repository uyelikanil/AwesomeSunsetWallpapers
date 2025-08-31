plugins {
    id("awesomesunsetwallpapers.multiplatform.core.library")
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