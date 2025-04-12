plugins {
    id("awesomesunsetwallpapers.android.library")
    id("awesomesunsetwallpapers.android.hilt")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.core.domain"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    testImplementation(project(":core:testing"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.paging.compose)
}