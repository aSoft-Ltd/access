package access.client.accounts

import access.account.CreateAccountParams
import access.account.Account
import access.user.UserRef
import later.Later

interface AccountsService {
    val scopes: AccountScopeService
    fun create(creator: UserRef, params: CreateAccountParams): Later<Account>
}