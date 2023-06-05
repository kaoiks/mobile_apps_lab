package com.example.cookbook


import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class Recipe(
    val id: Int,
    private val name: String,
    val type: String,
    val photo_url: String,
    val recipe_items: List<RecipeItem>,
    val recipe_steps: List<RecipeStep>
){
    fun getRecipeSteps(): String {
        val recipeStepsString = StringBuilder()

        for (step in recipe_steps) {

            recipeStepsString.append("${(step.step_number + 1)}. ${step.text}\n\n")
        }

        return recipeStepsString.toString()
    }

    fun getRecipeIngredients(): String{
        val recipeStepsString = StringBuilder()

        for (item in recipe_items) {

            recipeStepsString.append("${(item.title )} x ${item.quantity}\n")
        }

        return recipeStepsString.toString().trim()
    }

    fun getName(): String {
        return name
    }

    override fun toString(): String {
        return name
    }

//    companion object {
//        val recipes = getRecipes()
//    }
}

data class RecipeItem(
    val id: Int,
    val title: String,
    val quantity: String
)

data class RecipeStep(
    val id: Int,
    val text: String,
    val step_number: Int
)


fun getRecipes(): List<Recipe> {
    Log.d("HTTP", "FETCHING RECIPES")
    val client = OkHttpClient()

    val request = Request.Builder()
        .url("http://10.0.2.2:8000/recipes")
        .build()

    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) throw IOException("Unexpected code $response")

        val gson = Gson()
        val recipeList = gson.fromJson(response.body?.string(), Array<Recipe>::class.java).toList()

//        recipeList.forEach { recipe ->
//            recipe.recipe_items.forEach { item ->
//                println("Ingredient: ${item.title} - Quantity: ${item.quantity}")
//            }
//            recipe.recipe_steps.forEach { step ->
//                println("Step ${step.step_number}: ${step.text}")
//            }
//        }
        return recipeList
    }
}

fun getRecipe(id: Int): Recipe {
    Log.d("HTTP", "FETCHING RECIPES")
    val url = URL("http://10.0.2.2:8000/recipes/$id")
    with(url.openConnection() as HttpURLConnection) {
        requestMethod = "GET"

        inputStream.bufferedReader().use {
            val response = StringBuffer()

            var inputLine = it.readLine()
            while (inputLine != null) {
                response.append(inputLine)
                inputLine = it.readLine()
            }

            val gson = Gson()
            val recipe = gson.fromJson(response.toString(), Recipe::class.java)

            return recipe

        }
    }
}


//class Recipe(
//    private val name: String,
//    private val recipe: String) {
//
//    fun getRecipe(): String {
//        return recipe
//    }
//
//    fun getName(): String {
//        return name
//    }
//
//    override fun toString(): String {
//        return name
//    }
//
//    companion object {
//        val recipes = arrayOf(
//            Recipe("Pancakes", " 1. Mix flour, sugar, and baking powder in a bowl.\n 2. In a separate bowl, mix milk, egg, and melted butter.\n 3. Pour wet ingredients into dry ingredients and mix until just combined.\n 4. Cook on a greased skillet over medium heat."),
//            Recipe("Spaghetti Carbonara", " 1. Cook spaghetti in salted boiling water.\n 2. Fry pancetta or bacon until crispy.\n 3. Beat eggs and mix in grated Parmesan cheese.\n 4. Drain spaghetti and add to the pan with the pancetta.\n 5. Pour egg mixture over spaghetti and cook until sauce thickens."),
//            Recipe("Chocolate Cake", " 1. Preheat oven to 350°F.\n 2. Mix flour, sugar, cocoa powder, baking soda, and salt in a bowl.\n 3. In a separate bowl, mix water, oil, vinegar, and vanilla extract.\n 4. Pour wet ingredients into dry ingredients and mix until just combined.\n 5. Pour batter into a greased cake pan and bake for 30-35 minutes."),
//        )
//    }
//}
