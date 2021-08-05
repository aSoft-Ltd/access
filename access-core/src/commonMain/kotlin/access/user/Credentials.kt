package access.user

import contacts.Email
import contacts.Phone
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

@Serializable
sealed class Credentials

@Serializable
sealed class Basic : Credentials() {
    companion object {
        @JvmSynthetic
        operator fun invoke(identity: String, password: String) = of(identity, password)

        @JvmStatic
        fun of(identity: String, password: String): Basic = try {
            EmailPassword(identity, password)
        } catch (err: Throwable) {
            null
        } ?: try {
            PhonePassword(identity, password)
        } catch (err: Throwable) {
            null
        } ?: UsernamePassword(identity, password)
    }
}

@Serializable
data class EmailPassword(val email: Email, val password: String) : Basic() {
    constructor(email: String, password: String) : this(Email(email), password)
}

@Serializable
data class PhonePassword(val phone: Phone, val password: String) : Basic() {
    constructor(phone: String, password: String) : this(Phone(phone), password)
}

@Serializable
data class UsernamePassword(val username: String, val password: String) : Basic()