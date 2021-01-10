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
    gradlew("openjdk:11", "publish") {
        kotlinScript { api ->
            env["SPACE_USERNAME"] = api.spaceClientId()
            env["SPACE_PASSWORD"] = api.spaceSecret()
        }
    }
}