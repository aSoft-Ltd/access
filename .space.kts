job("Build & Test Project") {
    startOn {
        gitPush {
            enabled = true
            branchFilter {
                +"refs/heads/master-dev"
            }
        }
    }
    gradlew("openjdk:11", "build")
}

job("Deploy Project") {
    startOn {
        gitPush {
            enabled = true
            branchFilter {
                +"refs/heads/master"
            }
        }
    }
    gradlew("openjdk:11", "publish")
}