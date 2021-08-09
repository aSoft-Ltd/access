import access.system.IPrinciple
import access.user.User
import access.ISystemPermission.Companion.global
import access.account.Account
import expect.expect
import kotlin.test.Test

class PrincipleTest {

    private val testAccount = Account(name = "Test", type = "Type", uid = "<test>", scope = "Scope")

    @Test
    fun should_not_have_any_privilege() {
        val principle = object : IPrinciple {
            override val claims: Map<String, List<String>> = mapOf()
            override val account: Account = testAccount
        }
        expect(principle.has(User.Permissions.Delete, global)).toBe(false)
    }

    @Test
    fun developer_should_pass_any_privilege_check() {
        val principle = object : IPrinciple {
            override val claims: Map<String, List<String>> = mapOf(
                "system.developer" to listOf()
            )
            override val account = testAccount
        }
        expect(principle.has(User.Permissions.Wipe, global)).toBe(true)
    }

    @Test
    fun should_pass_only_when_has_a_specific_privilege() {
        val principle = object : IPrinciple {
            override val claims: Map<String, List<String>> = mapOf(
                User.Permissions.Create.title to listOf("account(1235)")
            )
            override val account = testAccount
        }
        expect(principle.has(User.Permissions.Create, "account(1235)")).toBe(true)
        expect(principle.has(User.Permissions.Create, "account(123)")).toBe(false)
    }

    @Test
    fun should_pass_only_when_has_a_specific_privilege_from_a_pool_of_privilege() {
        val principle = object : IPrinciple {
            override val claims: Map<String, List<String>> = mapOf(
                User.Permissions.Create.title to listOf("account(1235)", "account(2345)")
            )
            override val account = testAccount
        }
        expect(principle.has(User.Permissions.Create, "account(1235)")).toBe(true)
        expect(principle.has(User.Permissions.Create, "account(123)")).toBe(false)
        expect(principle.has(User.Permissions.Create, "account(2345)")).toBe(true)
    }

    @Test
    fun should_pass_for_any_account_if_has_a_global_privilege() {
        val principle = object : IPrinciple {
            override val claims: Map<String, List<String>> = mapOf(
                User.Permissions.Create.title to listOf("global")
            )
            override val account = testAccount
        }
        expect(principle.has(User.Permissions.Create, "account(1235)")).toBe(true)
        expect(principle.has(User.Permissions.Create, "user(1235)")).toBe(true)
    }
}