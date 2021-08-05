package access

import access.user.CreateUserParams
import access.user.User
import later.Later

class TestUsersService : UsersService {
    override fun create(creator: UserRef, params: CreateUserParams): Later<User> {
        TODO()
    }
}