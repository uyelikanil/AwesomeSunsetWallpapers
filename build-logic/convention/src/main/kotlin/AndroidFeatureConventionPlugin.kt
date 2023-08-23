import com.anilyilmaz.awesomesunsetwallpapers.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("awesomesunsetwallpapers.android.library")
                apply("awesomesunsetwallpapers.android.hilt")
            }

            dependencies {
                add("implementation", project(":core:network"))
                add("implementation", project(":core:domain"))
                add("implementation", project(":core:model"))
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:ui"))

                add("implementation", libs.findLibrary(
                    "androidx.lifecycle.viewmodel").get())
                add("implementation", libs.findLibrary(
                    "kotlinx.coroutines.android").get())
                add("implementation", libs.findLibrary(
                    "androidx.compose.foundation").get())
                add("implementation", libs.findLibrary(
                    "androidx.compose.material3").get())
                add("implementation", libs.findLibrary(
                    "androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary(
                    "androidx.lifecycle.runtime.compose").get())
                add("implementation", libs.findLibrary("coil.compose").get())
                add("implementation", libs.findLibrary(
                    "androidx.compose.ui.tooling.preview").get())
                add("debugImplementation", libs.findLibrary(
                    "androidx.compose.ui.tooling").get())
            }
        }
    }
}