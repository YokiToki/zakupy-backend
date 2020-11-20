buildscript {
    extra.apply{
        set("app.version", "0.1.0")
        set("kotlin.version", "1.4.10")
        set("logback.version", "1.2.1")
        set("ktor.server.version", "1.4.2")
        set("kotlin.dotenv.version", "6.2.1")
        set("koin.version", "2.2.1")
        set("icerockdev.email-service.version", "0.0.3")
        set("icerockdev.fcm-push-service.version", "0.0.2")
    }
    repositories {
        jcenter()
    }
    
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${project.extra["kotlin.version"]}")
        classpath("org.koin:koin-gradle-plugin:${project.extra["koin.version"]}")
    }
}

allprojects {
    repositories {
        mavenLocal()
        jcenter()
        maven { setUrl("https://kotlin.bintray.com/ktor") }
        maven { setUrl("https://dl.bintray.com/icerockdev/backend") }
    }
}

task<Delete>("clean") {
    delete(project.buildDir)
}
