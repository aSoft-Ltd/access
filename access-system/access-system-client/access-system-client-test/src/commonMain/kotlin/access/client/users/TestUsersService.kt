package access.client.users

import access.client.users.UsersService
import access.user.CreateUserParams
import access.user.User
import access.user.UserRef
import later.Later

class TestUsersService : UsersService {
    override fun create(creator: UserRef, params: CreateUserParams): Later<User> {
        TODO()
    }
}