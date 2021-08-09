package access.client.accounts

import access.account.AccountScope
import access.account.CreateAccountScopeParams
import access.user.UserRef
import kotlinx.atomic.collections.mutableAtomicListOf
import later.Later
import kotlin.jvm.JvmOverloads

class TestAccountScopeService @JvmOverloads constructor(
    private val scopes: MutableList<AccountScope> = mutableAtomicListOf()
) : AccountScopeService {
    override fun create(creator: UserRef, params: CreateAccountScopeParams): Later<AccountScope> = Later { res, _ ->
        val uid = "scope-${scopes.size + 1}"
        val scope = AccountScope(
            uid = uid,
            name = params.name,
            permissions = params.permissions
        )
        res(scope)
        scopes += scope
    }
}