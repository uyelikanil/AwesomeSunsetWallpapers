plugins {
    id("awesomesunsetwallpapers.multiplatform.library.core")
    id("awesomesunsetwallpapers.multiplatform.library.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:network"))
            implementation(project(":core:model"))

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
        }
    }
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.core.ui"
}