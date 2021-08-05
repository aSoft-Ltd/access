package access.system

import access.ISystemPermission
import access.account.UserAccount
import access.ISystemPermission.Companion.denied
import access.ISystemPermission.Companion.global

interface IPrinciple {
    val claims: Map<String, List<String>>
    val account: UserAccount

    fun has(permission: String, value: String): Boolean {
        val sysDev = claims["system.developer"]
        if (sysDev != null) {
            return !sysDev.contains(denied)
        }
        val perm = claims[permission]
        if (perm != null && perm.contains(global)) {
            return true
        }
        return perm?.contains(value) ?: false
    }

    fun has(permission: ISystemPermission, value: String) = has(permission.title, value)
}