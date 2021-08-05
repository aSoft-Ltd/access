package intergration

import access.TestUsersService
import access.UserRef
import access.user.Contacts
import access.user.CreateUserParams
import expect.expect
import later.await
import test.asyncTest
import kotlin.test.Test

class GivenAUserClientService {
    val service = TestUsersService()
    val tester = UserRef(uid = "<tester>", "Tester", null)

    @Test
    fun should_easily_create_a_user_from_a_service() = asyncTest {
        val params = CreateUserParams(
            name = "Test User",
            contacts = Contacts.None // Contacts.Email("test@email.com")
        )
        val user = service.create(tester, params).await()
        expect(user)
    }
}