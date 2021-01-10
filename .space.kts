job("Build & Test Project") {
    startOn {
        codeReviewOpened {
            enabled = true
        }
    }
<<<<<<< HEAD
    gradlew("openjdk:11", "build")
=======
    gradlew("build")
>>>>>>> d73755f4201f3d4dfac6c93855ec248236e2fc20
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
<<<<<<< HEAD
    gradlew("openjdk:11", "publish")
=======
    gradlew("publish")
>>>>>>> d73755f4201f3d4dfac6c93855ec248236e2fc20
}