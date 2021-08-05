package intergration

import access.client.TestUsersService
import access.user.UserRef
import access.user.Contacts
import access.user.CreateUserParams
import access.user.EmailPassword
import expect.expect
import later.await
import test.asyncTest
import kotlin.test.Test

class GivenAUserClientService {
    val service = TestUsersService()
    val tester = TESTER

    @Test
    fun should_easily_create_a_user_from_a_service() = asyncTest {
        val params = CreateUserParams(
            name = "Test User",
            contacts = Contacts("test@email.com"),
            credentials = EmailPassword("test@email.com", "123456")
        )
        val user = service.create(tester, params).await()
        expect(user)
    }
}