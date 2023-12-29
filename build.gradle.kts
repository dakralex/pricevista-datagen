import java.io.FileOutputStream
import java.nio.file.Files
import java.util.Properties

val cliktVersion = "4.+"
val ojdbc11Version = "23.+"
val slf4jVersion = "2.+"
val kotlinLoggingVersion = "5.+"
val kotlinSerializationJsonVersion = "1.+"

group = "org.dakralex.pricevista"
version = "1.0-SNAPSHOT"

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
    implementation("com.github.ajalt.clikt:clikt:$cliktVersion")
    implementation("com.oracle.database.jdbc:ojdbc11:$ojdbc11Version")
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("org.slf4j:slf4j-log4j12:$slf4jVersion")
    implementation("io.github.oshai:kotlin-logging-jvm:$kotlinLoggingVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinSerializationJsonVersion")
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

val resourcesDir = "src/resources"

sourceSets {
    main {
        resources {
            srcDirs(resourcesDir)
        }
    }
}

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
