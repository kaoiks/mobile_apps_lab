package com.example.cookbook

class Recipe(
    private val name: String,
    private val recipe: String) {

    fun getRecipe(): String {
        return recipe
    }

    fun getName(): String {
        return name
    }

    override fun toString(): String {
        return name
    }

    companion object {
        val recipes = arrayOf(
            Recipe("Pancakes", " 1. Mix flour, sugar, and baking powder in a bowl.\n 2. In a separate bowl, mix milk, egg, and melted butter.\n 3. Pour wet ingredients into dry ingredients and mix until just combined.\n 4. Cook on a greased skillet over medium heat."),
            Recipe("Spaghetti Carbonara", " 1. Cook spaghetti in salted boiling water.\n 2. Fry pancetta or bacon until crispy.\n 3. Beat eggs and mix in grated Parmesan cheese.\n 4. Drain spaghetti and add to the pan with the pancetta.\n 5. Pour egg mixture over spaghetti and cook until sauce thickens."),
            Recipe("Chocolate Cake", " 1. Preheat oven to 350°F. 2. Mix flour, sugar, cocoa powder, baking soda, and salt in a bowl. 3. In a separate bowl, mix water, oil, vinegar, and vanilla extract. 4. Pour wet ingredients into dry ingredients and mix until just combined. 5. Pour batter into a greased cake pan and bake for 30-35 minutes."),
        )
    }
}
