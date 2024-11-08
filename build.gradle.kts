import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val kotlinVersion = "2.1.0-Beta2"
val coroutinesVersion = "1.9.0-RC"
val serializationVersion = "1.7.3"


plugins {
	id("org.jetbrains.kotlin.jvm") version "2.1.0-Beta2"
	id("org.jetbrains.kotlin.plugin.serialization") version "2.1.0-Beta2"
	application
}


group = "org.example"
version = "1.0"




kotlin {
	jvmToolchain(17)
	compilerOptions.suppressWarnings = true
	compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
	compilerOptions.freeCompilerArgs.add("-Xwhen-guards")
	kotlin.compilerOptions.freeCompilerArgs.add("-Xmulti-dollar-interpolation")
}


repositories {
	mavenCentral()
}

dependencies {
	implementation("com.google.protobuf:protobuf-java-util:4.28.2")   // Deprecated in favour of kotlinx.serialization
	implementation("com.google.protobuf:protobuf-kotlin:4.28.2")      // Deprecated in favour of kotlinx.serialization
	testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:$serializationVersion")
	implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
	implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
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