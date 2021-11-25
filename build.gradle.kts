plugins {
    kotlin("jvm") version "1.6.0"
    id("org.springframework.boot") version "2.6.0"
    kotlin("plugin.spring") version "1.4.32"
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
    implementation("org.axonframework", "axon-spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.0")

    testImplementation("org.axonframework", "axon-test")
    testImplementation(platform("org.junit:junit-bom:5+"))
    testImplementation("org.junit.jupiter", "junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")
    testImplementation("org.hamcrest", "hamcrest", "2.2")
    testImplementation("org.mockito:mockito-core:4.1.0")

}
tasks {
    test {
        useJUnitPlatform()
    }
}