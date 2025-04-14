import com.anilyilmaz.awesomesunsetwallpapers.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "awesomesunsetwallpapers.android.library")
            apply(plugin = "awesomesunsetwallpapers.android.hilt")
            apply(plugin = "org.jetbrains.kotlin.plugin.serialization")

            dependencies {
                "implementation"(project(":core:network"))
                "implementation"(project(":core:domain"))
                "implementation"(project(":core:model"))
                "implementation"(project(":core:designsystem"))
                "implementation"(project(":core:ui"))

                "implementation"(libs.findLibrary("androidx.lifecycle.viewmodel").get())
                "implementation"(libs.findLibrary("kotlinx.coroutines.android").get())
                "implementation"(libs.findLibrary("androidx.compose.foundation").get())
                "implementation"(libs.findLibrary("androidx.compose.material3").get())
                "implementation"(libs.findLibrary("androidx.navigation.compose").get())
                "implementation"(libs.findLibrary("androidx.hilt.navigation.compose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.runtime.compose").get())
                "implementation"(libs.findLibrary("coil.compose").get())
                "implementation"(libs.findLibrary("androidx.compose.ui.tooling.preview").get())
                "debugImplementation"(libs.findLibrary("androidx.compose.ui.tooling").get())
                "implementation"(libs.findLibrary("kotlinx.serialization.json").get())
            }
        }
    }
}