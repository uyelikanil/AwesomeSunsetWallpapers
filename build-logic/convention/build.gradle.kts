plugins {
    `kotlin-dsl`
}

group = "com.anilyilmaz.awesomesunsetwallpapers.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "awesomesunsetwallpapers.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "awesomesunsetwallpapers.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("compose") {
            id = "awesomesunsetwallpapers.compose"
            implementationClass = "ComposeConventionPlugin"
        }
        register("androidFeature") {
            id = "awesomesunsetwallpapers.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidHilt") {
            id = "awesomesunsetwallpapers.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
    }
}