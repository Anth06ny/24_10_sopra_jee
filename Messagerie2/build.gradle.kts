import org.springframework.boot.gradle.tasks.bundling.BootWar

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
    war
}
val springCloudVersion by extra("2024.0.1")

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

tasks.named<BootWar>("bootWar") {
    archiveFileName.set("monExecutable.war")
}

repositories {
    mavenCentral()
}

dependencies {

    //Sécurité
    implementation("org.springframework.boot:spring-boot-starter-security")

    //docu avec Swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.+")

    implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")

    //Permet à JAVA de se connecter à une base SQL
    runtimeOnly("com.h2database:h2")

    //WebSocket
    implementation("org.springframework.boot:spring-boot-starter-websocket")

//JPA Framework Java qui génère du SQL
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")


}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}
dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
