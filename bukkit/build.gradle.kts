dependencies {
    compileOnly(libs.spigot)
    implementation(libs.packetevents.spigot)
    implementation(project(":common"))
}

tasks {
    jar {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        from (
            configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
        )
    }
}
