package com.example.cookbook.fragments


import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.cookbook.DetailActivityCallback
import com.example.cookbook.R
import com.example.cookbook.Recipe
import com.example.cookbook.getRecipe
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.net.URL


class RecipeDetailFragment : Fragment() {
    private var recipeId: Long = 0
    private lateinit var shareButton: FloatingActionButton
    private lateinit var recipeIngretiens: String
    private lateinit var recipe: Recipe
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            recipeId = savedInstanceState.getLong("recipeId")

        }
        else{
            val stoper = StoperFragment()
            val ft: FragmentTransaction = childFragmentManager.beginTransaction()
            ft.add(R.id.stoper_container, stoper)
            ft.addToBackStack(null)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedinstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shareButton = view.findViewById<FloatingActionButton>(R.id.fab_share)
        shareButton.setOnClickListener {
            Log.d("SHARING", "SHARE BUTTON PRESSED")
            shareRecipe(recipe.getRecipeIngredients())
        }
    }
    fun setRecipe(id: Int) {
        this.recipeId = id.toLong()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putLong("recipeId", recipeId)
    }

    override fun onStart() {
        super.onStart()
        val view = view
        if (view != null) {
            recipe = getRecipe(recipeId.toInt())
            val title = view.findViewById<View>(R.id.textTitle) as TextView
            Log.d("DEV_DEBUG", recipeId.toString())
//            val recipe: Recipe = getRecipe(recipeId.toInt())
            title.text = recipe.getName()

            val ingredients = view.findViewById<View>(R.id.textIngreditens) as TextView
            ingredients.text = recipe.getRecipeIngredients()

            val description = view.findViewById<View>(R.id.textDescription) as TextView
            description.text = recipe.getRecipeSteps()
//            val imageRecipe = view.findViewById<ImageView>(R.id.imageRecipe)
            val url = URL(recipe.photo_url)
            val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
//            imageRecipe.setImageBitmap(bmp)

            val callback = activity as? DetailActivityCallback
            callback?.updateNavigationBarImage(bmp)


        }
    }

    fun shareRecipe(text: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(shareIntent, "Share text using"))
    }

}
