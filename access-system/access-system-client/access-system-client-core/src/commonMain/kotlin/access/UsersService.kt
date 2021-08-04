package access

import access.user.User
import later.Later

interface UsersService {
    fun create(creator: UserRef): Later<User>
}