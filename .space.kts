job("Build & Test Project") {
    startOn {
        codeReviewOpened {
            enabled = true
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