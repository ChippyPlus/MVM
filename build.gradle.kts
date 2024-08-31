plugins {
    kotlin("jvm") version "2.0.20"
}


tasks.jar {
    manifest.attributes["Main-Class"] = "org.helloWorld.MainKt"
    manifest.attributes["Class-Path"] = configurations
        .runtimeClasspath.get()
        .joinToString(separator = " ") { file ->
            "libs/${file.name}"
        }
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}