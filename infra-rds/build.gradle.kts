
tasks.getByName("bootJar") {
	enabled = false
}

tasks.getByName("jar") {
	enabled = true
}

dependencies {
	runtimeOnly("com.h2database:h2")
	runtimeOnly("org.postgresql:postgresql")
}
