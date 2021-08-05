package access.user

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic
import contacts.Email as ValidEmail
import contacts.Phone as ValidPhone

@Serializable
sealed class Contacts {

    companion object {
        @JvmSynthetic
        operator fun invoke(vararg values: String) = of(*values)

        private fun instantiate(contact: String): Contacts = try {
            Email(contact)
        } catch (err: Throwable) {
            null
        } ?: try {
            Phone(contact)
        } catch (err: Throwable) {
            null
        } ?: throw IllegalArgumentException("$contact is not a valid email or phone")

        @JvmStatic
        fun of(vararg values: String): Contacts {
            var contacts: Contacts = None
            for (value in values) {
                contacts += instantiate(value)
            }
            return contacts
        }
    }

    @Serializable
    object None : Contacts()

    @Serializable
    data class Email(val email: ValidEmail) : Contacts() {
        constructor(email: String) : this(ValidEmail(email))
    }

    @Serializable
    data class Emails(val emails: List<ValidEmail>) : Contacts() {
        constructor(vararg emails: String) : this(emails.map { ValidEmail(it) })
    }

    @Serializable
    data class Phone(val phone: ValidPhone) : Contacts() {
        constructor(phone: String) : this(ValidPhone(phone))
    }

    @Serializable
    data class Phones(val phones: List<ValidPhone>) : Contacts() {
        constructor(vararg phones: String) : this(phones.map { ValidPhone(it) })
    }

    @Serializable
    data class EmailPhone(val email: ValidEmail, val phone: ValidPhone) : Contacts() {
        constructor(email: String, phone: String) : this(ValidEmail(email), ValidPhone(phone))
    }

    @Serializable
    data class Mixed(val emails: List<ValidEmail>, val phones: List<ValidPhone>) : Contacts()

    operator fun plus(other: Contacts): Contacts = when (this) {
        None -> other
        is Email -> when (other) {
            None -> this
            is Email -> Emails(email.toString(), other.email.toString())
            is Emails -> Emails(emails = listOf(email) + other.emails)
            is Phone -> EmailPhone(email, other.phone)
            is Phones -> Mixed(emails = listOf(email), phones = other.phones)
            is EmailPhone -> Mixed(emails = listOf(email) + other.email, phones = listOf(other.phone))
            is Mixed -> Mixed(emails = listOf(email) + other.emails, phones = other.phones)
        }
        is Emails -> when (other) {
            None -> this
            is Email -> Emails(emails + other.email)
            is Emails -> Emails(emails + other.emails)
            is Phone -> Mixed(emails, phones = listOf(other.phone))
            is Phones -> Mixed(emails, phones = other.phones)
            is EmailPhone -> Mixed(emails + other.email, phones = listOf(other.phone))
            is Mixed -> Mixed(emails + other.emails, phones = other.phones)
        }
        is Phone -> when (other) {
            None -> this
            is Email -> EmailPhone(other.email.toString(), phone.toString())
            is Emails -> Mixed(emails = other.emails, phones = listOf(phone))
            is Phone -> Phones(phone.toString(), other.phone.toString())
            is Phones -> Phones(phones = listOf(phone) + other.phones)
            is EmailPhone -> Mixed(emails = listOf(other.email), phones = listOf(phone, other.phone))
            is Mixed -> Mixed(emails = other.emails, phones = listOf(phone) + other.phones)
        }
        is Phones -> when (other) {
            None -> this
            is Email -> Mixed(listOf(other.email), phones)
            is Emails -> Mixed(other.emails, phones)
            is Phone -> Phones(phones + other.phone)
            is Phones -> Phones(phones + other.phones)
            is EmailPhone -> Mixed(emails = listOf(other.email), phones = phones + other.phone)
            is Mixed -> Mixed(emails = other.emails, phones = phones + other.phones)
        }
        is EmailPhone -> when (other) {
            None -> this
            is Email -> Mixed(listOf(email) + other.email, listOf(phone))
            is Emails -> Mixed(listOf(email) + other.emails, listOf(phone))
            is Phone -> Mixed(listOf(email), listOf(phone) + other.phone)
            is Phones -> Mixed(listOf(email), listOf(phone) + other.phones)
            is EmailPhone -> Mixed(listOf(other.email) + other.email, listOf(phone) + other.phone)
            is Mixed -> Mixed(listOf(email) + other.emails, listOf(phone) + other.phones)
        }
        is Mixed -> when (other) {
            None -> this
            is Email -> Mixed(emails + other.email, phones)
            is Emails -> Mixed(emails + other.emails, phones)
            is Phone -> Mixed(emails, phones + other.phone)
            is Phones -> Mixed(emails, phones + other.phones)
            is EmailPhone -> Mixed(emails + other.email, phones + other.phone)
            is Mixed -> Mixed(emails + other.emails, phones + other.phones)
        }
    }
}