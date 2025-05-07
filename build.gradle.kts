plugins {
    kotlin("jvm") version "2.1.10"

    kotlin("plugin.serialization") version "2.1.20"

}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.0")
    //google truth
    testImplementation("com.google.truth:truth:1.4.2")
    //coroutine test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3") // or your version
    //mockk
    testImplementation("io.mockk:mockk:1.13.16")
    //di
    implementation("io.insert-koin:koin-core:4.0.4")

    // Kotlin coroutine dependency
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // MongoDB Kotlin driver dependency
    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:4.10.1")

    implementation("io.ktor:ktor-client-core:3.1.3")

    implementation("io.ktor:ktor-client-cio:3.1.3")

    implementation("ch.qos.logback:logback-classic:1.5.6")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}