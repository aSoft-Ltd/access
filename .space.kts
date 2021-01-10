job("Build & Test Project") {
    startOn {
        codeReviewOpened {
            enabled = true
        }
    }
    gradlew("build")
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
    gradlew("publish")
}