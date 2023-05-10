package com.example.cookbook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.ListFragment
import android.R as RR
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager


class RecipeListFragment : ListFragment() {

    var listener: OnListItemClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListItemClickListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnListItemClickListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        listener?.onListItemClick(id)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val names = arrayOfNulls<String>(Recipe.recipes.size)
        for (i in names.indices) {
            names[i] = Recipe.recipes[i].getName()
        }
        val adapter: ArrayAdapter<String> = ArrayAdapter(
            inflater.context, RR.layout.simple_list_item_1, names
        )
        listAdapter = adapter
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    interface OnListItemClickListener {
        fun onListItemClick(id: Long)
    }
}