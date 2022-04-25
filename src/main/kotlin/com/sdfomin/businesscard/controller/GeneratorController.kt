package com.sdfomin.businesscard.controller

import com.sdfomin.businesscard.generator.BusinessCardGenerator
import org.springframework.core.io.Resource
import org.springframework.http.*
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView


@Controller
class GeneratorController(
    val businessCardGenerator: BusinessCardGenerator,
) {

    @GetMapping(value = [""])
    fun main(): ModelAndView {
        return ModelAndView("index")
    }

    @PostMapping(value = ["/generate"])
    fun generate(
        @RequestParam(value = "name") name: String,
        @RequestParam(value = "telegram") telegram: String,
        @RequestParam(value = "profession") profession: String,
    ): ResponseEntity<Resource> =
        businessCardGenerator.generate(
            name = name,
            telegram = telegram,
            profession = profession,
        )
}
