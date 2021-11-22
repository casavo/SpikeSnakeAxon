plugins {
    kotlin("jvm") version "1.6.0"
}

group = "com.casavo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val javaVersion = JavaVersion.VERSION_17
java {
    sourceCompatibility = javaVersion
    targetCompatibility = javaVersion
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation(platform("org.axonframework:axon-bom:4.5.+"))
    implementation("org.axonframework", "axon-configuration")
    implementation("org.axonframework", "axon-eventsourcing")
    implementation("org.axonframework", "axon-messaging")
    implementation("org.axonframework", "axon-modelling")

    testImplementation("org.axonframework", "axon-test")
    testImplementation(platform("org.junit:junit-bom:5+"))
    testImplementation("org.junit.jupiter", "junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")
    testImplementation("org.hamcrest", "hamcrest", "2.2")
}
tasks {
    test {
        useJUnitPlatform()
    }
}