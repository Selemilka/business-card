package com.sdfomin.businesscard.security

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import java.security.Principal

@RestController
class ManageResource {

    @GetMapping("/manage")
    fun home(principal: Principal?): String {
        val name = if (principal != null) principal.name else ""
        return "<h1>Welcome $name</h1>"
    }

    @GetMapping("/manage/user")
    fun user(): String {
        return "<h1>Welcome User</h1>"
    }

    @GetMapping("/manage/admin")
    fun admin(): String {
        return "<h1>Welcome Admin</h1>"
    }
}
