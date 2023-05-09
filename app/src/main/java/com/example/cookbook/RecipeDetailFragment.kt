package com.example.cookbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class RecipeDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedinstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }
}
