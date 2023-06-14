package com.example.cookbook.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.cookbook.R

class ContainerFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set the initial fragment
        setFragment(RecipeListFragment())

        // Example of swapping between fragments
        view.findViewById<View>(R.id.button_first_fragment)?.setOnClickListener {
            setFragment(RecipeListFragment())
        }

        view.findViewById<View>(R.id.button_second_fragment)?.setOnClickListener {
            setFragment(RecipeListFragment())
        }

        view.findViewById<View>(R.id.button_third_fragment)?.setOnClickListener {
            setFragment(DessertListFragment())
        }
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}