@file:UseSerializers(LongAsStringSerializer::class)

package access.user

import access.ISystemPermission
import access.account.UserAccount
import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.builtins.LongAsStringSerializer

@Serializable
data class User(
    val uid: String,
    val name: String,
    val tag: String,
    val contacts: Contacts,
    val photoUrl: String?,
    val status: Status = Status.SignedOut,
    val accounts: List<UserAccount>,
    val verifiedContacts: Contacts,
    val registeredOn: Long = Clock.System.now().toEpochMilliseconds(),
    val lastSeen: Long = Clock.System.now().toEpochMilliseconds(),
    val deleted: Boolean = false
) {
    enum class Status {
        Blocked,
        SignedIn,
        SignedOut
    }

    enum class Permissions(
        override val title: String,
        override val details: String,
        override val needs: List<String> = listOf(),
    ) : ISystemPermission {
        Read(
            title = "authentication.users.read",
            details = "Grants access to view/edit users in the system"
        ),
        Create(
            title = "authentication.users.create",
            details = "Grants access to create different users for the system",
            needs = listOf(Read.title)
        ),
        Update(
            title = "authentication.users.update",
            details = "Grants access to update user information",
            needs = listOf(Read.title)
        ),
        Delete(
            title = "authentication.users.delete",
            details = "Grants access to delete users from the system",
            needs = listOf(Read.title)
        ),
        Wipe(
            title = "authentication.users.wipe",
            details = "Grants access to permanently wipe users from the system",
            needs = listOf(Read.title)
        )
    }

    fun ref() = UserRef(
        uid = uid,
        name = name,
        photoUrl = photoUrl
    )
}