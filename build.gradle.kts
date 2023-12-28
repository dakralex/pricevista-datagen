val cliktVersion = "4.+"
val ojdbcVersion = "23.+"

plugins {
    kotlin("jvm") version "1.9.21"
    application
}

group = "org.dakralex.pricevista.datagen"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.ajalt.clikt:clikt:$cliktVersion")
    implementation("com.oracle.database.jdbc:ojdbc11:$ojdbcVersion")
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("org.dakralex.pricevista.datagen.DatagenCLIKt")
}

tasks.jar {
    manifest {
        attributes("Main-Class" to application.mainClass)
    }
}