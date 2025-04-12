package com.anilyilmaz.awesomesunsetwallpapers

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures.apply {
            compose = true
        }

        dependencies {
            add("implementation", platform(libs.findLibrary(
                "androidx-compose-bom").get()))
        }
    }
}