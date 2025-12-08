plugins {
    id("awesomesunsetwallpapers.multiplatform.library.core")
    id("awesomesunsetwallpapers.multiplatform.library.compose")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(compose.components.resources)
        }
    }
}

compose {
    resources {
        publicResClass = true
        packageOfResClass = "com.anilyilmaz.awesomesunsetwallpapers.core.resource"
    }
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.core.resource"
}