package intergration.accounts

import access.account.AccountPermission
import access.account.CreateAccountScopeParams
import access.client.accounts.TestAccountScopeService
import expect.expect
import intergration.TESTER
import later.await
import test.asyncTest
import kotlin.test.Test

class GivenAnAccountScopesService {
    val service = TestAccountScopeService()
    val tester = TESTER

    @Test
    fun should_easily_create_account_scopes() = asyncTest {
        val params = CreateAccountScopeParams(
            name = "Test Scope",
            (1..5).map { AccountPermission("permission-$it", "Detail $it") }
        )
        val scope = service.create(tester, params).await()
        expect(scope).toBeNonNull()
        expect(scope.permissions).toBeOfSize(5)
    }
}