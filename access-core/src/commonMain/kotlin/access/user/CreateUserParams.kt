package access.user

import kotlinx.serialization.Serializable

@Serializable
class CreateUserParams(
    val name: String,
    val contacts: Contacts,
    val credentials: Credentials
)