pluginManagement {
    repositories {
        includeBuild("build-logic")
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "AwesomeSunsetWallpapers"
include(":core:model")
include(":core:network")
include(":core:data")
include(":core:common")
include(":core:domain")
include(":core:designsystem")
include(":core:ui")
include(":feature:home")
include(":feature:main")
include(":feature:wallpaperdetail")
include(":core:testing")
include(":core:resource")
include(":composeApp")
include(":core:system")
