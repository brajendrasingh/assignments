plugins {
	java
	id("org.springframework.boot") version "4.2.0-M2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.bs"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(27)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	//actuator for metrics
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	//H2 database
	implementation("com.h2database:h2:2.5.252")
	//For h2 db console only
	implementation("org.springframework.boot:spring-boot-h2console")
	// Spring Web
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	// Spring data
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-data-jpa-test")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testCompileOnly("org.projectlombok:lombok")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testAnnotationProcessor("org.projectlombok:lombok")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
