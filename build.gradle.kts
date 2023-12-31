import org.gradle.nativeplatform.platform.internal.Architectures
import org.gradle.nativeplatform.platform.internal.DefaultNativePlatform
import java.io.FileOutputStream
import java.util.*

val cliktVersion = "4.+"
val slf4jVersion = "2.+"
val brotliVersion = "1.15.0"
val ojdbc11Version = "23.+"
val kotlinLoggingVersion = "5.+"
val kotlinSerializationJsonVersion = "1.+"

val operatingSystem: OperatingSystem = DefaultNativePlatform.getCurrentOperatingSystem()

group = "org.dakralex.pricevista"
version = "0.9.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.serialization") version "1.9.21"
    application
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("org.slf4j:slf4j-log4j12:$slf4jVersion")
    implementation("io.github.oshai:kotlin-logging-jvm:$kotlinLoggingVersion")
    implementation("com.github.ajalt.clikt:clikt:$cliktVersion")
    implementation("com.oracle.database.jdbc:ojdbc11:$ojdbc11Version")
    implementation("com.aayushatharva.brotli4j:brotli4j:$brotliVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationJsonVersion")
    runtimeOnly(
        "com.aayushatharva.brotli4j:native-" +
                if (operatingSystem.isWindows) {
                    if (DefaultNativePlatform.getCurrentArchitecture().isArm) {
                        "windows-aarch64"
                    } else {
                        "windows-x86_64"
                    }
                } else if (operatingSystem.isMacOsX) {
                    if (DefaultNativePlatform.getCurrentArchitecture().isArm) {
                        "osx-aarch64"
                    } else {
                        "osx-x86_64"
                    }
                } else if (operatingSystem.isLinux) {
                    if (Architectures.ARM_V7.isAlias(DefaultNativePlatform.getCurrentArchitecture().name)) {
                        "linux-armv7"
                    } else if (Architectures.AARCH64.isAlias(DefaultNativePlatform.getCurrentArchitecture().name)) {
                        "linux-aarch64"
                    } else if (Architectures.X86_64.isAlias(DefaultNativePlatform.getCurrentArchitecture().name)) {
                        "linux-x86_64"
                    } else {
                        throw IllegalStateException("Unsupported architecture: ${DefaultNativePlatform.getCurrentArchitecture().name}")
                    }
                } else {
                    throw IllegalStateException("Unsupported operating system: $operatingSystem")
                } + ":$brotliVersion"
    )

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("$group.DatagenCLIKt")
}

tasks {
    test {
        useJUnitPlatform()
    }

    jar {
        manifest {
            attributes("Main-Class" to application.mainClass)
        }
    }
}

val resourcesDir = "src/main/resources"

tasks.register("generateVersionProperties") {
    group = JavaBasePlugin.BUILD_TASK_NAME
    description = "Generates version.properties file for internal version management"

    inputs.property("version", "$version")
    outputs.dir(resourcesDir)

    doLast {
        val resource = file("$resourcesDir/version.properties")
        resource.parentFile.mkdirs()

        val properties = Properties()
        properties.setProperty("version", "$version")

        properties.store(FileOutputStream(resource), null)
    }
}

tasks.named("processResources") {
    dependsOn("generateVersionProperties")
}
