package access.account

import kotlinx.serialization.Serializable

@Serializable
class CreateAccountScopeParams(
    val name: String,
    val permissions: List<AccountPermission>
) {
    constructor(name: String, vararg permissions: AccountPermission) : this(name, permissions.toList())
}