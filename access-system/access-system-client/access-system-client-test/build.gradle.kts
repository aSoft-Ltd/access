plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}


kotlin {
    jvm { library() }
    js(IR) { library() }
    nativeTargets(true)

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":access-system-client-core"))
            }
        }
    }
}

aSoftOSSLibrary(
    version = vers.asoft.access,
    description = "A Kotlin Multiplatform Library for writting tests to access a system from any client"
)