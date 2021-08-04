package access.user

import contacts.Email
import contacts.Phone

sealed class Contact {
    sealed class SingleEmail(val email: Email) : Contact()
    sealed class ManyEmails(val emails: List<Email>) : Contact()
    sealed class SinglePhone(val phone: Email) : Contact()
    sealed class ManyPhone(val phones: List<Phone>) : Contact()
    sealed class EmailPhone(val email: Email, val phone: Phone) : Contact()
    sealed class Mixed(val emails: List<Email>, phones: List<Phone>)
}