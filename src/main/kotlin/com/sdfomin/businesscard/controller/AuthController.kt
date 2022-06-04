package com.sdfomin.businesscard.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import java.security.Principal

@Controller(value = "")
class AuthController {

    @GetMapping(value = ["/login2"])
    fun main(): ModelAndView {
        return ModelAndView("auth/auth")
    }

    @PostMapping(value = ["/login2"])
    fun login(
        principal: Principal?,
        @RequestParam(value = "login") login: String,
        @RequestParam(value = "password", required = true) password: String,
    ): ModelAndView {
        println("$login $password")
        return ModelAndView("auth/auth")
    }

    @PostMapping(value = ["/register2"])
    fun register(
        principal: Principal?,
        @RequestParam(value = "login") login: String,
        @RequestParam(value = "password", required = true) password: String,
    ): ModelAndView {
        println("$login $password")
        return ModelAndView("auth/auth")
    }
}
