plugins {
    id("awesomesunsetwallpapers.android.library")
    id("awesomesunsetwallpapers.android.koin")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.core.data"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:domain"))
    testImplementation(project(":core:testing"))

    implementation(libs.kotlinx.coroutines.android)
}