package intergration.accounts

import access.account.CreateAccountParams
import access.client.accounts.TestAccountsService
import expect.expect
import intergration.TESTER
import later.await
import test.asyncTest
import kotlin.test.Test

class GivenAnAccountsClientService {
    val service = TestAccountsService()
    val tester = TESTER

    @Test
    fun should_easily_create_an_account_from_a_service() = asyncTest {
        val params = CreateAccountParams(
            name = "Test User",
        )
        val user = service.create(tester, params).await()
        expect(user)
    }
}