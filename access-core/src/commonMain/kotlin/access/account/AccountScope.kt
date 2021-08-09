package access.account

import kotlinx.serialization.Serializable

@Serializable
data class AccountScope(
    val uid: String,
    val name: String,
    val permissions: List<AccountPermission>
)