plugins {
    id("awesomesunsetwallpapers.multiplatform.feature")
    id("awesomesunsetwallpapers.multiplatform.library.compose")
    id("awesomesunsetwallpapers.android.feature.koin")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:resource"))
        }
    }
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.feature.wallpaperdetail"
}

dependencies {
    testImplementation(project(":core:data"))
    testImplementation(project(":core:testing"))
    implementation(libs.androidx.activity.compose)
    implementation(compose.components.resources)
    testImplementation(libs.androidx.test.runner)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.junit4)
}