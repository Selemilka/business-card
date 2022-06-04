package com.sdfomin.businesscard.controller

import com.sdfomin.businesscard.db.service.UserService
import com.sdfomin.businesscard.manager.ShowCardManager
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView
import java.security.Principal

@RestController
class ShowCardController(
    private val showCardManager: ShowCardManager,
    private val userService: UserService,
) {


    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = ["/manage/list"])
    fun main(principal: Principal?): ModelAndView {

        assert(principal?.name != null) { "User must not be null!" }

        val userId = userService.loadUser(principal!!.name).id
        val businessCards = showCardManager.loadAllCards(userId)

        val modelAndView = ModelAndView("index")
        modelAndView.addObject("cards", businessCards)

        return modelAndView
    }

    @GetMapping(value = ["/{handle}"])
    fun show(@PathVariable handle: String): ModelAndView {
        val card = showCardManager.loadWebCard(handle)
        val modelAndView = ModelAndView("card/card")
        modelAndView.addObject("card", card)
        return modelAndView
    }

}
