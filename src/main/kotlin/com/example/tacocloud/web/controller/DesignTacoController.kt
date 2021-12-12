package com.example.tacocloud.web.controller

import com.example.tacocloud.domain.Ingredient
import com.example.tacocloud.domain.Ingredient.Type
import com.example.tacocloud.domain.Taco
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.SessionAttributes
import java.util.ArrayList
import java.util.stream.Collectors


/**
 * @author sun-yixin
 */
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
class DesignTacoController {

    private final val logger = LoggerFactory.getLogger(this::class.java);

    @ModelAttribute
    fun addIngredientsToModel(model: Model) {
        logger.info("$this addIngredientsToModel starts")
        val ingredients: List<Ingredient> = arrayListOf(
            Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
            Ingredient("COTO", "Corn Tortilla", Type.WRAP),
            Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
            Ingredient("CARN", "Carnitas", Type.PROTEIN),
            Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
            Ingredient("LETC", "Lettuce", Type.VEGGIES),
            Ingredient("CHED", "Cheddar", Type.CHEESE),
            Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
            Ingredient("SLSA", "Salsa", Type.SAUCE),
            Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        )
        val types: Array<Type> = Type.values()
        for (type in types) {
            model.addAttribute(
                type.toString().lowercase(),
                filterByType(ingredients, type)
            )
        }
    }

    @GetMapping
    fun showDesignForm(model: Model): String? {
        model.addAttribute("taco", Taco("",ArrayList()))
        return "design"
    }

    private fun filterByType(
        ingredients: List<Ingredient>, type: Type
    ): Iterable<Ingredient>? {
        return ingredients
            .stream()
            .filter { type.equals(it) }
            .collect(Collectors.toList())
    }
}