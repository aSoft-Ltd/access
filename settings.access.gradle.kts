include(":access-core")
include(":access-system-core")
project(":access-system-core").projectDir = File("access/access-system/access-system-core")

include(":access-system-client-core")
project(":access-system-client-core").projectDir =
    File("access/access-system/access-system-client/access-system-client-core")
include(":access-system-client-test")
project(":access-system-client-test").projectDir =
    File("access/access-system/access-system-client/access-system-client-test")
