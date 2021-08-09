package access.client.accounts

import access.account.CreateAccountParams
import access.account.Account
import access.client.accounts.AccountsService
import access.user.UserRef
import kotlinx.atomic.collections.mutableAtomicListOf
import later.Later
import kotlin.jvm.JvmOverloads

class TestAccountsService @JvmOverloads constructor(
    private val accounts: MutableList<Account> = mutableAtomicListOf(),
    override val scopes: AccountScopeService = TestAccountScopeService()
) : AccountsService {
    override fun create(creator: UserRef, params: CreateAccountParams) = Later<Account> { res, _ ->
        val account = Account(
            uid = "account-${accounts.size}",
            name = params.name,
            scope = "Scope?",
            type = "Type?"
        )
        res(account)
        accounts.add(account)
    }
}