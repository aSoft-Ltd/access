plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
}

group = "tz.co.asoft"
version = vers.asoft.access

kotlin {
    multiplatformLib()
    sourceSets {
        val commonMain by getting {
            dependencies {
                api("org.jetbrains.kotlinx:kotlinx-datetime:${vers.kotlinx.datetime}")
                api(asoft("persist-core", vers.asoft.persist))
                api(asoft("phone-core", vers.asoft.contacts))
                api(asoft("email-core", vers.asoft.contacts))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(asoft("test-core", vers.asoft.test))
            }
        }
    }
}

configurePublishing {
    repositories {
        maven("https://maven.pkg.jetbrains.space/asofttz/p/libs/maven") {
            name = "space"
            credentials {
                username = System.getenv("SPACE_USERNAME") ?: System.getenv("JB_SPACE_CLIENT_ID")
                password = System.getenv("SPACE_PASSWORD") ?: System.getenv("JB_SPACE_CLIENT_SECRET")
            }
        }
    }
}