package com.example.tacocloud

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 * @author sun-yixin
 */
@Controller
class HomeController {

    @GetMapping("/")
    fun home(): String {
        return "home"
    }
}