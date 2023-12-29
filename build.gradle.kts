plugins {
    kotlin("jvm") version "1.9.21"
    kotlin("plugin.serialization") version "1.9.21"
    application
}

group = "org.dakralex.pricevista"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.github.ajalt.clikt:clikt:4.+")
    implementation("com.oracle.database.jdbc:ojdbc11:23.+")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("$group.DatagenCLIKt")
}

tasks.jar {
    manifest {
        attributes("Main-Class" to application.mainClass)
    }
}