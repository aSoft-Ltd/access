package access

import access.user.CreateUserParams
import access.user.User
import later.Later

interface UsersService {
    fun create(creator: UserRef, params: CreateUserParams): Later<User>
}