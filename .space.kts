job("Build & Test Project") {
    container("ubuntu:21.04") {
        kotlinScript { api ->
            if (api.gitBranch() == "refs/heads/master-dev") {
                api.gradlew("build")
            } else {
                println("Code submitted")
            }
        }
    }
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
    gradlew("gradle:6.7.1-jdk11", "publish")
}