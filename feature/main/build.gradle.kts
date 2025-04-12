plugins {
    id("awesomesunsetwallpapers.android.library")
    id("awesomesunsetwallpapers.android.hilt")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.feature.main"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    testImplementation(project(":core:testing"))

    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.kotlinx.coroutines.android)
}