package access.system

import access.user.User
import access.account.UserAccount
import kotlinx.serialization.Serializable

@Serializable
sealed class Principle : IPrinciple {
    @Serializable
    data class UserPrinciple(
        override val user: User,
        override val account: UserAccount,
        override val claims: Map<String, List<String>>
    ) : Principle(), IUserPrinciple

    @Serializable
    data class ClientAppPrinciple(
        override val app: ClientApp,
        override val account: UserAccount,
        override val claims: Map<String, List<String>>
    ) : Principle(), IClientAppPrinciple
}