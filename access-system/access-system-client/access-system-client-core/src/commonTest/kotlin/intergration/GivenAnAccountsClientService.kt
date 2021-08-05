package intergration

import access.account.CreateAccountParams
import access.client.TestAccountsService
import access.client.TestUsersService
import access.user.UserRef
import access.user.Contacts
import access.user.CreateUserParams
import access.user.EmailPassword
import expect.expect
import later.await
import test.asyncTest
import kotlin.test.Test

class GivenAnAccountsClientService {
    val service = TestAccountsService()
    val tester = TESTER

    @Test
    fun should_easily_create_a_user_from_a_service() = asyncTest {
        val params = CreateAccountParams(
            name = "Test User",
        )
        val user = service.create(tester, params).await()
        expect(user)
    }
}