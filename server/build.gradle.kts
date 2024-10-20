val kotlin_version: String by project
val logback_version: String by project
val kotlinx_html_version: String by project

plugins {
	kotlin("jvm") version "2.0.21"
	id("io.ktor.plugin") version "3.0.0"
	id("org.jetbrains.kotlin.plugin.serialization") version "2.0.21"
}

group = "mvm"
version = "0.0.1"

application {
	mainClass.set("mvm.ApplicationKt")

	val isDevelopment: Boolean = project.ext.has("development")
	applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
	mavenCentral()
	maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers") }
}

dependencies {
	implementation("io.ktor:ktor-server-core-jvm")
	implementation("io.ktor:ktor-server-auth-jvm")
	implementation("io.ktor:ktor-server-content-negotiation-jvm")
	implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
	implementation("io.ktor:ktor-server-auto-head-response-jvm")
	implementation("io.ktor:ktor-server-resources-jvm")
	implementation("io.ktor:ktor-server-host-common-jvm")
	implementation("io.ktor:ktor-server-default-headers-jvm")
	implementation("io.ktor:ktor-server-http-redirect-jvm")
	implementation("io.ktor:ktor-server-html-builder-jvm")
	implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinx_html_version")
	implementation("org.jetbrains:kotlin-css-jvm:1.0.0-pre.129-kotlin-1.4.20")
	implementation("io.ktor:ktor-server-netty-jvm")
	implementation("ch.qos.logback:logback-classic:$logback_version")
	testImplementation("io.ktor:ktor-server-test-host-jvm")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
