package com.example.cookbook


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.floatingactionbutton.FloatingActionButton


class RecipeDetailFragment : Fragment() {
    private var recipeId: Long = 0
    private lateinit var shareButton: FloatingActionButton
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
            val recipe: Recipe = Recipe.recipes[recipeId.toInt()]
            shareRecipe(recipe.getRecipe())
        }
    }
    fun setRecipe(id: Long) {
        this.recipeId = id
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putLong("recipeId", recipeId)
    }

    override fun onStart() {
        super.onStart()
        val view = view
        if (view != null) {
            val title = view.findViewById<View>(R.id.textTitle) as TextView

            val recipe: Recipe = Recipe.recipes[recipeId.toInt()]
            title.text = recipe.getName()
            val description = view.findViewById<View>(R.id.textDescription) as TextView
            description.text = recipe.getRecipe()
        }
    }

    fun shareRecipe(text: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(shareIntent, "Share text using"))
    }

}
