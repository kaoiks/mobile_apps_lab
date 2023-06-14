package com.example.cookbook


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import com.example.cookbook.fragments.ContainerFragment
import com.example.cookbook.fragments.DessertListFragment
import com.example.cookbook.fragments.RecipeDetailFragment
import com.example.cookbook.fragments.RecipeListFragment


class MainActivity : AppCompatActivity(), RecipeListFragment.OnItemClickListener, DessertListFragment.OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("APP_LOGS", "Initialized view")
        var toolbar = findViewById<View>(R.id.toolbar)
        if (toolbar == null) {
            setSupportActionBar(toolbar as Toolbar?)
        }

        if (savedInstanceState == null) {
            val fragment = ContainerFragment()
//            fragment.listener = this
        }



    }

    override fun onItemClick(position: Int) {
        Log.d("DEV_DEBUG","ITEM CLICKED")
        val fragmentContainer = findViewById<View>(R.id.fragment_container_2)
        if (fragmentContainer != null) {
            val details = RecipeDetailFragment()
            details.setRecipe(position)
            intent.putExtra(DetailActivity.EXTRA_RECIPE_ID, position)
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragment_container_2, details)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.addToBackStack(null)
            ft.commit()

        } else {
            Log.d("APP_LOGS", "PHONE DETECTED")
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_RECIPE_ID, position)
            startActivity(intent)
        }
    }

}