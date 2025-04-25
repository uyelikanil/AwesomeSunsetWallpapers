import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {`kotlin-dsl`
}

group = "com.anilyilmaz.awesomesunsetwallpapers.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_17
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
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
        register("androidApplicationCompose") {
            id = "awesomesunsetwallpapers.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "awesomesunsetwallpapers.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeature") {
            id = "awesomesunsetwallpapers.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("jvmLibrary") {
            id = "awesomesunsetwallpapers.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("androidKoin") {
            id = "awesomesunsetwallpapers.android.koin"
            implementationClass = "AndroidKoinConventionPlugin"
        }
        register("androidFeatureKoin") {
            id = "awesomesunsetwallpapers.android.feature.koin"
            implementationClass = "AndroidFeatureKoinConventionPlugin"
        }
    }
}