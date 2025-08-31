plugins {
    id("awesomesunsetwallpapers.multiplatform.core.library")
    id("awesomesunsetwallpapers.multiplatform.koin")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:model"))
            implementation(libs.kotlinx.coroutines.core)
        }

        commonTest.dependencies {
            implementation(project(":core:testing"))
            implementation(kotlin("test"))
        }
    }
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.core.domain"
}