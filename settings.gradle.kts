pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
    }
}

rootProject.name = "visual-driver"
include("bukkit")
include("fabric")
include("common")
include("test-plugin")
