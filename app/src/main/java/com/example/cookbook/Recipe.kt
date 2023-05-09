package com.example.cookbook

class Recipe(
    private val name: String,
    private val recipe: String) {

    public final Recipe[]


    fun getRecipe(): String {
        return recipe
    }

    fun getName(): String {
        return name
    }

    override fun toString(): String {
        return name
    }
}