job("Build/Deploy Project") {
    container("gradle:6.7.1-jdk11") {
        kotlinScript { api ->
            when {
                api.gitBranch() == "refs/heads/master" -> {
                    api.gradlew(":publish")
                }
                api.gitBranch() == "refs/heads/master-dev" -> {
                    api.gradlew("build")
                }
            }
        }
    }
}