package com.example.cookbook

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception
import java.net.URL
import com.example.cookbook.RecipeListFragment.OnItemClickListener

class RecipeAdapter(private val context: Context, private val recipes: ArrayList<Recipe>) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

        val recipeArrayList: ArrayList<Recipe>
        private var itemClickListener: OnItemClickListener? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // to inflate the layout for each item of recycler view.
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_captioned_image, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val recipe: Recipe = recipeArrayList[position]

            holder.recipeName.text = recipe.getName()
            holder.recipeId.text = recipe.id.toString()

            try {
                val url = URL(recipe.photo_url)
                val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                holder.recipeImage.setImageBitmap(bmp)
            }
            catch(_: Exception){
                holder.recipeImage.setImageResource(R.drawable.ic_image_not_found)
            }

            holder.itemView.setOnClickListener {
                itemClickListener?.onItemClick(recipe.id)
            }

        }

        fun setOnItemClickListener(listener: OnItemClickListener) {
            itemClickListener = listener
        }
        override fun getItemCount(): Int {
            return recipeArrayList.size
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val recipeImage: ImageView
            val recipeName: TextView
            val recipeId: TextView
            init {

                recipeImage = itemView.findViewById(R.id.recipe_image_view)
                recipeName = itemView.findViewById(R.id.recipe_name_text_view)
                recipeId = itemView.findViewById(R.id.recipe_id)
            }
        }
        fun updateData(newRecipes: ArrayList<Recipe>) {
            recipes.clear()
            recipes.addAll(newRecipes)
            notifyDataSetChanged()
        }




        init {
            this.recipeArrayList = recipes
            recipeArrayList.addAll(recipes)
        }

}