package com.example.cookbook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val frag: RecipeDetailFragment = supportFragmentManager.findFragmentById(R.id.detail_frag) as RecipeDetailFragment

        val recipeId = (intent.extras!![EXTRA_RECIPE_ID] as Int).toLong()
        frag.setRecipe(recipeId.toInt())

    }

    companion object {
        val EXTRA_RECIPE_ID: String = "id"
    }
}