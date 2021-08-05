package access.client

import access.account.CreateAccountParams
import access.account.UserAccount
import access.user.UserRef
import later.Later

class TestAccountsService : AccountsService {
    override fun create(creator: UserRef, params: CreateAccountParams): Later<UserAccount> {
        TODO("Not yet implemented")
    }
}