plugins {
    id("awesomesunsetwallpapers.multiplatform.core.library")
    id("awesomesunsetwallpapers.multiplatform.koin")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:network"))
            implementation(project(":core:common"))
            implementation(project(":core:model"))
            implementation(project(":core:domain"))

            implementation(libs.kotlinx.coroutines.core)
        }

        commonTest.dependencies {
            implementation(project(":core:testing"))
        }
    }
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.core.data"
}