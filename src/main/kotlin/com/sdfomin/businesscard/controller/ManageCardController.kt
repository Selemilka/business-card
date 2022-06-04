package com.sdfomin.businesscard.controller

import com.sdfomin.businesscard.db.service.UserService
import com.sdfomin.businesscard.entity.AppleCardAttributes
import com.sdfomin.businesscard.entity.BusinessCard
import com.sdfomin.businesscard.entity.Contacts
import com.sdfomin.businesscard.manager.BusinessCardManager
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.view.RedirectView
import java.security.Principal


@Controller
class ManageCardController(
    private val businessCardManager: BusinessCardManager,
    private val userService: UserService,
) {

//    @PostMapping(value = ["/customize"])
//    fun customize(
//        @RequestParam(value = "fontColor") fontColor: String,
//        @RequestParam(value = "backgroundColor") backgroundColor: String,
//    ): ModelAndView {
//        return ModelAndView("customize")
//    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = ["/manage/create"])
    fun create(): ModelAndView = ModelAndView("card/create")

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = ["/manage/create"])
    fun create(
        principal: Principal?,
        @RequestParam(value = "handle") handle: String,
//        @RequestParam(value = "logoImage") logoImage: String?, TODO logoImage
        @RequestParam(value = "description") description: String,
        @RequestParam(value = "businessIsActive", required = false) businessIsActive: String?,
        @RequestParam(value = "businessName", required = false) businessName: String?,
        @RequestParam(value = "businessWebsite", required = false) businessWebsite: String?,
        @RequestParam(value = "businessEmail", required = false) businessEmail: String?,
        @RequestParam(value = "businessPhone", required = false) businessPhone: String?,
        @RequestParam(value = "businessTelegram", required = false) businessTelegram: String?,
        @RequestParam(value = "personIsActive", required = false) personIsActive: String?,
        @RequestParam(value = "personName", required = false) personName: String?,
        @RequestParam(value = "personWebsite", required = false) personWebsite: String?,
        @RequestParam(value = "personEmail", required = false) personEmail: String?,
        @RequestParam(value = "personPhone", required = false) personPhone: String?,
        @RequestParam(value = "personTelegram", required = false) personTelegram: String?,
    ): RedirectView {
        businessCardManager.createCard(
            businessCard = BusinessCard(
                userId = userService.loadUser(principal!!.name).id,
                handle = handle,
                logoImage = null, // TODO logoImage
                description = description,
                businessContacts = if (businessIsActive != "on") Contacts() else Contacts(
                    isActive = true,
                    name = businessName,
                    website = businessWebsite,
                    email = businessEmail,
                    phone = businessPhone,
                    telegram = businessTelegram,
                ),
                personContacts = if (personIsActive != "on") Contacts() else Contacts(
                    isActive = true,
                    name = personName,
                    website = personWebsite,
                    email = personEmail,
                    phone = personPhone,
                    telegram = personTelegram,
                ),
                appleCardAttributes = AppleCardAttributes()
            )
        )
        return RedirectView("/manage/list")
    }

//    @PostMapping(value = ["/download"])
//    fun downloadPass() : ResponseEntity<Resource> = businessCardManager.generate()
}
