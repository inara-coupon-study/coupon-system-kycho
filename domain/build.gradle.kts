
tasks.getByName("bootJar") {
	enabled = false
}

tasks.getByName("jar") {
	enabled = true
}

plugins {
	kotlin("plugin.jpa") version "1.6.21"
}

dependencies {
	implementation(project(":infra-rds"))

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}
