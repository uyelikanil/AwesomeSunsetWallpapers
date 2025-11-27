plugins {
    id("awesomesunsetwallpapers.application")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:resource"))
            implementation(compose.components.resources)
            implementation(project(":core:common"))
            implementation(project(":core:network"))
            implementation(project(":core:data"))
            implementation(project(":core:domain"))
            implementation(project(":core:designsystem"))
            implementation(project(":core:model"))
            implementation(project(":feature:main"))
            implementation(project(":feature:home"))
            implementation(project(":feature:wallpaperdetail"))

            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.jetbrains.androidx.navigation.compose)
        }

        androidMain.dependencies {
            implementation(libs.androidx.ktx)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.lifecycle.runtime.compose)
        }
    }
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.composeApp"
}
