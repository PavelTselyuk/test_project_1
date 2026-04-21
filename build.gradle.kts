plugins {
    id("java")
    kotlin("jvm")
    id("io.qameta.allure") version "3.2" +
            ".0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("org.junit.jupiter:junit-jupiter-api:6.0.3")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:6.0.3")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.34.0")
    testImplementation("com.codeborne:selenide:7.14.0")
    testImplementation("io.github.bonigarcia:webdrivermanager:6.1.0")

    testImplementation("io.qameta.allure:allure-selenide:2.27.0")
    testImplementation("io.rest-assured:rest-assured:5.5.6")


    testImplementation("io.qameta.allure:allure-junit5:2.29.1")
    testImplementation("io.qameta.allure:allure-commandline:2.30.0")
    testImplementation("io.qameta.allure:allure-assertj:2.29.1")
    testImplementation("io.qameta.allure:allure-rest-assured:2.29.1")
    testImplementation("io.qameta.allure:allure-java-commons:2.29.1")
    testImplementation("org.aspectj:aspectjweaver:1.9.24")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(24)
}

tasks.test {
    useJUnitPlatform()
    systemProperty("allure.results.directory", "build/allure-results")
}