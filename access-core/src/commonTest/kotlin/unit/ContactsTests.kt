package unit

import access.user.Contacts
import contacts.Phone
import expect.expect
import expect.toBe
import kotlin.test.Test

class ContactsTests {
    @Test
    fun should_change_a_none_contact_into_an_email_contact_when_added_with_an_email() {
        val email = "test@email.com"
        val initialContact = Contacts.None
        val finalContact = initialContact + Contacts.Email(email)
        expect(finalContact).toBe<Contacts.Email>()
        val emailContact = finalContact as Contacts.Email
        expect(emailContact.email.toString()).toBe(email)
    }

    @Test
    fun should_change_a_none_contact_into_a_phone_when_added_with_a_phone() {
        val phone = "255752748674"
        val initial = Contacts.None
        val final = initial + Contacts.Phone(phone)
        expect(final).toBe<Contacts.Phone>()
        val phoneContact = final as Contacts.Phone
        expect(phoneContact.phone.toString()).toBe(phone)
    }

    @Test
    fun should_change_an_email_contact_into_emails_when_another_email_is_added() {
        val email1 = "test1@test.com"
        val email2 = "test2@test.com"
        val initial = Contacts.Email(email1)
        val final = initial + Contacts.Email(email2)
        expect(final).toBe<Contacts.Emails>()
        val finalContacts = final as Contacts.Emails
        expect(finalContacts.emails.map { it.toString() }).toBe(listOf(email1, email2))
    }

    @Test
    fun should_instantiate_an_email_contact_smartly() {
        val email = "test@test.com"
        val contact = Contacts(email)
        expect(contact).toBe<Contacts.Email>()
        val emailContact = contact as Contacts.Email
        expect(emailContact.email.toString()).toBe(email)
    }

    @Test
    fun should_instantiate_an_email_phone_contact_smartly() {
        val email = "test@email.com"
        val phone = "255752748674"
        val contacts = Contacts(email, phone)
        expect(contacts).toBe<Contacts.EmailPhone>()
        val emailPhone = contacts as Contacts.EmailPhone
        expect(emailPhone.email.toString()).toBe(email)
        expect(emailPhone.phone.toString()).toBe(phone)
    }

    @Test
    fun should_instantiate_a_mixed_contact_smartly() {
        val email1 = "test1@email.com"
        val email2 = "test2@email.com"
        val phone = "255752748674"
        val contacts = Contacts(email1, phone, email2)
        expect(contacts).toBe<Contacts.Mixed>()
        val emailPhone = contacts as Contacts.Mixed
        expect(emailPhone.emails.map { it.toString() }).toBe(listOf(email1, email2))
        expect(emailPhone.phones.map { it.toString() }).toBe(listOf(phone))
    }
}