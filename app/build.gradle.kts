plugins {
    kotlin("jvm")
}

group = "com.github.yokitoki.zakupy"
version = rootProject.extra["app.version"] as String


dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${rootProject.extra["kotlin.version"]}")
    implementation("io.github.cdimascio:dotenv-kotlin:${rootProject.extra["kotlin.dotenv.version"]}")
    implementation("ch.qos.logback:logback-classic:${rootProject.extra["logback.version"]}")
    implementation("io.ktor:ktor-server-netty:${rootProject.extra["ktor.server.version"]}")
    implementation("io.ktor:ktor-server-core:${rootProject.extra["ktor.server.version"]}")
    implementation("io.ktor:ktor-locations:${rootProject.extra["ktor.server.version"]}")
    implementation("io.ktor:ktor-server-host-common:${rootProject.extra["ktor.server.version"]}")
    implementation("io.ktor:ktor-auth:${rootProject.extra["ktor.server.version"]}")
    implementation("io.ktor:ktor-auth-jwt:${rootProject.extra["ktor.server.version"]}")
    implementation("io.ktor:ktor-jackson:${rootProject.extra["ktor.server.version"]}")
    testImplementation("io.ktor:ktor-server-tests:${rootProject.extra["ktor.server.version"]}")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}