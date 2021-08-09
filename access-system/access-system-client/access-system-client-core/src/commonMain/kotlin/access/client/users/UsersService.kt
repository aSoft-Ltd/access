package access.client.users

import access.user.CreateUserParams
import access.user.User
import access.user.UserRef
import later.Later

interface UsersService {
    fun create(creator: UserRef, params: CreateUserParams): Later<User>
}