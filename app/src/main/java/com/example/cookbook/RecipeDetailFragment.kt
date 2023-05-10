package com.example.cookbook


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction


class RecipeDetailFragment : Fragment() {
    private var recipeId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            recipeId = savedInstanceState.getLong("recipeId")

        }else{
            val stoper = StoperFragment()
            val ft: FragmentTransaction = childFragmentManager.beginTransaction()
            ft.add(R.id.stoper_container, stoper)
            ft.addToBackStack(null)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedinstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
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

            val cocktail: Recipe = Recipe.recipes[recipeId.toInt()]
            title.setText(cocktail.getName())
            val description = view.findViewById<View>(R.id.textDescription) as TextView
            description.setText(cocktail.getRecipe())
        }
    }

}
