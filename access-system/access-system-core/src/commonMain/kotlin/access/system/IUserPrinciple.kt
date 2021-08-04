package access.system

import access.user.User

interface IUserPrinciple : IPrinciple {
    val user: User
}