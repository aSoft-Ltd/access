package access.client

import access.account.CreateAccountParams
import access.account.UserAccount
import access.user.UserRef
import later.Later

interface AccountsService {
    fun create(creator: UserRef, params: CreateAccountParams): Later<UserAccount>
}