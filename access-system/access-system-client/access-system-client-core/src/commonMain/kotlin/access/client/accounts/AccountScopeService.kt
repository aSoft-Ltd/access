package access.client.accounts

import access.account.AccountScope
import access.account.CreateAccountScopeParams
import access.user.UserRef
import later.Later

interface AccountScopeService {
    fun create(creator: UserRef, params: CreateAccountScopeParams): Later<AccountScope>
}