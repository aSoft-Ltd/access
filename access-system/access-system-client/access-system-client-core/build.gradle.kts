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
                api(project(":access-system-core"))
                api(asoft("later-ktx", vers.asoft.later))
            }
        }

        val commonTest by getting {
            dependencies {
                api(project(":access-system-client-test"))
                implementation(asoft("expect-coroutines", vers.asoft.expect))
            }
        }
    }
}

aSoftOSSLibrary(
    version = vers.asoft.access,
    description = "A Kotlin Multiplatform Library with entities required for access to a system from any client"
)