package access.system

fun Collection<ISystemPermission>.hasPermit(name: String): Boolean {
    if (any { it.title == "system.developer" }) return true
    if (any { it.title == name }) return true
    return false
}

fun Collection<ISystemPermission>.hasPermit(permit: ISystemPermission) = hasPermit(permit.title)