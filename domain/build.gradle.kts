
tasks.getByName("bootJar") {
	enabled = false
}

tasks.getByName("jar") {
	enabled = true
}

dependencies {
	implementation(project(":infra-rds"))

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}
