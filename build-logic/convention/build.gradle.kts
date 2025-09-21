import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.anilyilmaz.awesomesunsetwallpapers.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.compose.compiler.gradlePlugin)
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
        register("multiplatformLibraryCompose") {
            id = "awesomesunsetwallpapers.multiplatform.library.compose"
            implementationClass = "MultiplatformLibraryComposeConventionPlugin"
        }
        register("androidFeature") {
            id = "awesomesunsetwallpapers.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("jvmLibrary") {
            id = "awesomesunsetwallpapers.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("androidFeatureKoin") {
            id = "awesomesunsetwallpapers.android.feature.koin"
            implementationClass = "AndroidFeatureKoinConventionPlugin"
        }
        register("multipaltformLibraryKoin") {
            id = "awesomesunsetwallpapers.multiplatform.library.koin"
            implementationClass = "MultiplatformLibraryKoinConventionPlugin"
        }
        register("multipaltformLibraryCore") {
            id = "awesomesunsetwallpapers.multiplatform.library.core"
            implementationClass = "MultiplatformLibraryCoreConventionPlugin"
        }
    }
}