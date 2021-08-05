package access.user

import kotlinx.serialization.Serializable

@Serializable
class UserRef(
    val uid: String,
    val name: String,
    val photoUrl: String?
)