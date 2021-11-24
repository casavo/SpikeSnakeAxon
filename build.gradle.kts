plugins {
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.allopen") version "1.6.0"
    id("io.quarkus") version "2.4.0.Final"
    id("de.undercouch.download") version "4.1.2"
}

group = "com.casavo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()

}

val javaVersion = JavaVersion.VERSION_11
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
    implementation(enforcedPlatform("io.quarkus.platform:quarkus-bom:2.4.0.Final"))
    implementation("io.quarkus:quarkus-rest-client-jackson")
    implementation("io.quarkus:quarkus-rest-client")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-resteasy")
    implementation("io.quarkus:quarkus-resteasy-jackson")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.quarkus:quarkus-arc")
    implementation("com.fasterxml.jackson.module","jackson-module-kotlin","2.13.0")

    testImplementation("org.axonframework", "axon-test")
    testImplementation(platform("org.junit:junit-bom:5+"))
    testImplementation("org.junit.jupiter", "junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")
    testImplementation("org.hamcrest", "hamcrest", "2.2")
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
}

allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = javaVersion.toString()
    kotlinOptions.javaParameters = true
}

tasks {
    test {
        useJUnitPlatform()
    }
}