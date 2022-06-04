package com.sdfomin.businesscard.configuration

import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Component
class WebMvcConfiguration : WebMvcConfigurer {

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/login").setViewName("auth/auth")
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE)
    }

}
