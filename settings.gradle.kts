pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }

    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }
}

rootProject.name = "access"
include(":access-core")
include(":access-system-core")
project(":access-system-core").projectDir = File("access-system/access-system-core")

include(":access-system-client-core")
project(":access-system-client-core").projectDir = File("access-system/access-system-client/access-system-client-core")
