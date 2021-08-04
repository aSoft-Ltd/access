package access.system

import access.ISystemPermission
import access.UserAccount
import kotlinx.serialization.Serializable

@Serializable
data class ClientApp(
    val uid: String,
    val name: String,
    val secret: String,
    val account: UserAccount,
    val hosts: List<String>,
    val deleted: Boolean = false
) {
    enum class Permissions(
        override val title: String,
        override val details: String,
        override val needs: List<String> = listOf(),
    ) : ISystemPermission {
        Read(
            title = "authentication.apps.read",
            details = "Grants access to view/edit apps in the system"
        ),
        Create(
            title = "authentication.apps.create",
            details = "Grants access to create different apps for the system",
            needs = listOf(Read.title)
        ),
        Update(
            title = "authentication.apps.update",
            details = "Grants access to update app information",
            needs = listOf(Read.title)
        ),
        Delete(
            title = "authentication.apps.delete",
            details = "Grants access to delete apps from the system",
            needs = listOf(Read.title)
        ),
        Wipe(
            title = "authentication.apps.wipe",
            details = "Grants access to wipe permanently apps from the system",
            needs = listOf(Read.title)
        )
    }
}