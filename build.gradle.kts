plugins {
    kotlin("jvm") version "2.0.20"
    kotlin("plugin.serialization") version "2.0.20"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

kotlin {
    jvmToolchain(17)
    compilerOptions.suppressWarnings = true
    kotlin.compilerOptions.freeCompilerArgs.add("-Xmulti-dollar-interpolation")
}


repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
}

tasks.jar {
    manifest.attributes["Main-Class"] = "MainKt"
}

tasks.test {
    enabled = false
}


val fatJar = tasks.create("FatJar", Jar::class) {
    group = "better build"
    description = "Creates a self-contained fat JAR."
    manifest.attributes["Main-Class"] = "MainKt"
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    repositories {
        mavenCentral()
    }

    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get())
}

tasks.startScripts {
    dependsOn(fatJar)
}
application {
    mainClass.set("MainKt")
}

tasks.register<JavaExec>("r") {
    group = "execution"
    description = "Runs the MVM from the fat JAR."

    dependsOn(fatJar)

    classpath = files(fatJar.archiveFile)
    mainClass.set(application.mainClass.get())

}

tasks {
    "build" {
        dependsOn(fatJar)
    }
}