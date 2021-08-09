package access.system

import access.user.User
import access.account.Account
import kotlinx.serialization.Serializable

@Serializable
sealed class Principle : IPrinciple {
    @Serializable
    data class UserPrinciple(
        override val user: User,
        override val account: Account,
        override val claims: Map<String, List<String>>
    ) : Principle(), IUserPrinciple

    @Serializable
    data class ClientAppPrinciple(
        override val app: ClientApp,
        override val account: Account,
        override val claims: Map<String, List<String>>
    ) : Principle(), IClientAppPrinciple
}