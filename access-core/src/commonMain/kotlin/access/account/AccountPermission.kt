package access.account

import kotlinx.serialization.Serializable

@Serializable
data class AccountPermission(
    val name: String,
    val details: String,
    val needs: List<String> = listOf()
) {
    companion object {
        val DEV: AccountPermission = AccountPermission(
            name = "system.developer",
            details = "Grant's permissions to every action on the system (Should only be used in development)"
        )
    }
}