package com.example.cookbook

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.cookbook.fragments.RecipeDetailFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout


class DetailActivity : AppCompatActivity(), DetailActivityCallback {

    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val appBarLayout: AppBarLayout = findViewById(R.id.appBarLayout)
        toolbar = findViewById(R.id.toolbar)


        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val scrollRange = appBarLayout.totalScrollRange
            val offsetPercentage = Math.abs(verticalOffset).toFloat() / scrollRange.toFloat()

            // Calculate the new height of the toolbar
            val newToolbarHeight = (toolbar.height * (1 - offsetPercentage)).toInt()

            // Set the new height for the toolbar
            toolbar.layoutParams.height = newToolbarHeight
            toolbar.requestLayout()
        })

        setSupportActionBar(toolbar)
        val frag: RecipeDetailFragment = supportFragmentManager.findFragmentById(R.id.detail_frag) as RecipeDetailFragment
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        val recipeId = (intent.extras!![EXTRA_RECIPE_ID] as Int).toLong()
        frag.setRecipe(recipeId.toInt())

    }


    override fun updateNavigationBarImage(imageResource: Bitmap) {
//        val collapsingToolbarLayout: CollapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout)
//        val imageView: ImageView = findViewById(R.id.imageView)
//
//// Set the desired image resource
//        imageView.setImageResource(R.drawable.your_image)
//
//        collapsingToolbarLayout.background = imageView.drawable
//
//        val drawable = BitmapDrawable(resources, imageResource)
        val collapsingToolbarLayout: CollapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout)

// Load the image from a resource or URL
        val drawable = BitmapDrawable(resources, imageResource)
        collapsingToolbarLayout.background = drawable
        toolbar.background = drawable

//        imageView.setImageBitmap(imageResource)
//        imageView.scaleType = ImageView.ScaleType.FIT_XY
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        val EXTRA_RECIPE_ID: String = "id"
    }


}

interface DetailActivityCallback {
    fun updateNavigationBarImage(imageResource: Bitmap)
}
