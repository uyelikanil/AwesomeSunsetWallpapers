plugins {
    id("awesomesunsetwallpapers.multiplatform.library.core")
    id("awesomesunsetwallpapers.multiplatform.library.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
        }
    }
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.core.designsystem"
}