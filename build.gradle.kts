plugins {
    kotlin("jvm") version "2.0.10"
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

application {
    mainClass.set("es.prog2425.dam1.calculadora.MainKt")
}

group = "prog2425.dam1.prueba-calc-propio"
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
kotlin {
    jvmToolchain(21)
}