import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.4"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.8.0"
	kotlin("plugin.spring") version "1.8.0"
	kotlin("plugin.allopen") version "1.8.0"
	kotlin("plugin.jpa") version "1.8.0"
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.Embeddable")
	annotation("jakarta.persistence.MappedSuperclass")
}

group = "com.techbyfloig.cubannotary"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.postgresql:postgresql:42.3.8")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation ("org.springframework.boot:spring-boot-starter-security")
	implementation ("org.springframework.boot:spring-boot-starter-web")
	implementation ("com.google.api-client:google-api-client:1.32.1")
	implementation ("com.google.http-client:google-http-client-jackson2:1.38.0")
	runtimeOnly("com.h2database:h2")
	implementation ("io.jsonwebtoken:jjwt-api:0.11.2")
	implementation ("io.jsonwebtoken:jjwt-impl:0.11.2")
	implementation ("io.jsonwebtoken:jjwt-jackson:0.11.2")
	implementation("org.apache.pdfbox:pdfbox:2.0.28")
	implementation("org.zeroturnaround:zt-zip:1.14")
	implementation ("org.springframework.boot:spring-boot-starter-webflux")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
