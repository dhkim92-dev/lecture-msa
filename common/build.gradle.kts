plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.0"
    id("io.spring.dependency-management") version "1.1.6"
//    kotlin("plugin.jpa") version "1.9.25"
}

version = "1.0"

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2024.0.0"

dependencies {
    api("io.netty:netty-resolver-dns-native-macos:4.1.105.Final:osx-aarch_64")
    api("org.springframework.boot:spring-boot-starter")
    api("org.springframework.boot:spring-boot-starter-web")
    api("org.springframework.boot:spring-boot-starter-webflux")
    api("org.springframework.boot:spring-boot-starter-validation")
    api("com.fasterxml.jackson.module:jackson-module-kotlin")
    api("org.jetbrains.kotlin:kotlin-reflect")
    testApi("org.springframework.boot:spring-boot-starter-test")
    testApi("org.jetbrains.kotlin:kotlin-test-junit5")
    testApi("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testApi("io.mockk:mockk:1.13.4")
    testApi("io.kotest:kotest-assertions-core-jvm:5.9.1")
    testApi("io.kotest:kotest-runner-junit5-jvm:5.9.1")
    testApi(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(19)
}