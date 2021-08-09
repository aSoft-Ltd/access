package access.account

import kotlinx.serialization.Serializable

@Serializable
class CreateAccountParams(
    val name: String
)