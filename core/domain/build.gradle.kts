plugins {
    id("awesomesunsetwallpapers.android.library")
    id("awesomesunsetwallpapers.android.koin")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.core.domain"
}

dependencies {
    implementation(project(":core:model"))
    testImplementation(project(":core:testing"))

    implementation(libs.kotlinx.coroutines.android)
    testImplementation(kotlin("test"))
}