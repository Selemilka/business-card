package com.sdfomin.businesscard.db.service

import com.sdfomin.businesscard.db.dto.ContactsRepo
import com.sdfomin.businesscard.db.dto.convert
import com.sdfomin.businesscard.entity.Contacts
import org.springframework.stereotype.Component

@Component
class ContactsService(
    private val repo: ContactsRepo,
) {
    fun save(contacts: Contacts) = repo.save(contacts.convert())
}
