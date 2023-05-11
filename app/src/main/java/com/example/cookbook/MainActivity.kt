package com.example.cookbook


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity(), RecipeListFragment.OnListItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var toolbar = findViewById<View>(R.id.toolbar)
        if (toolbar == null) {
            setSupportActionBar(toolbar as Toolbar?)
        }
//        else{
//            toolbar = findViewById<View>(R.id.toolbar_2)
//            setSupportActionBar(toolbar as Toolbar?)
//        }
        if (savedInstanceState == null) {
            val fragment = RecipeListFragment()
            fragment.listener = this
        }


    }

//    fun onShowDetail(view: View?) {
//        val intent = Intent(this, DetailActivity::class.java)
//        startActivity(intent)
//    }
//
//
//    fun itemClicked(id: Long) {
//        val intent = Intent(this, DetailActivity::class.java)
//        intent.putExtra(DetailActivity.EXTRA_RECIPE_ID,  id.toInt());
//        startActivity(intent)
//    }

    override fun onListItemClick(id: Long) {
        val fragmentContainer = findViewById<View>(R.id.fragment_container)
        if (fragmentContainer != null) {
            val details = RecipeDetailFragment()
            details.setRecipe(id)
            val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragment_container, details)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.addToBackStack(null)
            ft.commit()

        } else {
            Log.d("APP_LOGS", "PHONE DETECTED")
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_RECIPE_ID, id)
            startActivity(intent)
        }
    }

    interface OnListItemClickListener {
        fun onListItemClick(id: Long)
    }
}