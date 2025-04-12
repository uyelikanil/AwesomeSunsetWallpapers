plugins {id("awesomesunsetwallpapers.android.library")
}

android {
    namespace = "com.anilyilmaz.awesomesunsetwallpapers.core.testing"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:model"))

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.paging.compose)
    api(libs.androidx.test.runner)
    api(libs.kotlinx.coroutines.test)
    api(libs.androidx.paging.test)
    api(libs.junit4)
}