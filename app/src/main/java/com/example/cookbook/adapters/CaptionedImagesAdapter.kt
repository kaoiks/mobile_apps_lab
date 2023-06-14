//package com.example.cookbook
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//
//class CaptionedImagesAdapter(private val captions: Array<String>, private val imageIds: IntArray) :
//    RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val itemView = LayoutInflater.from(parent.context)
//            .inflate(R.layout.list_item, parent, false)
//        return ViewHolder(itemView)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val caption = captions[position]
//        val imageId = imageIds[position]
//        holder.bind(caption, imageId)
//    }
//
//    override fun getItemCount(): Int {
//        return captions.size
//    }
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
//        private val textView: TextView = itemView.findViewById(R.id.textView)
//
//        fun bind(caption: String, imageId: Int) {
//            imageView.setImageResource(imageId)
//            textView.text = caption
//        }
//    }
//}