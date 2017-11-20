package com.example.ben.corppopdemoapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.example.ben.corppopdemoapp.databinding.RvItemImageBinding
import com.example.ben.corppopdemoapp.model.Photo
import com.squareup.picasso.Picasso

/**
 * Adapter to display the images returned from the search
 */
class ImageListAdapter(private var items: List<Photo>, private var listener: OnItemClickListener)
    : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = RvItemImageBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    interface OnItemClickListener {
        fun onItemClick(item: Photo)
    }

    class ViewHolder(private var binding: RvItemImageBinding) :
            RecyclerView.ViewHolder(binding.root) {
        lateinit var ivFlikrImage: ImageView
        fun bind(image: Photo, listener: OnItemClickListener?) {
            binding.image = image
            if (listener != null) {
                binding.btnImageDetails.setOnClickListener({ _ -> listener.onItemClick(image) })
            }
            ivFlikrImage = binding.ivFlikrImg
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int, payloads: MutableList<Any>?) {
        super.onBindViewHolder(holder, position, payloads)
        val currentItem = items[position]
        //PopulateImageView
        Picasso.with(holder?.itemView?.context)
                .load(Utils.generateImageUrl(currentItem.farm.toString(), currentItem.server!!, currentItem.id!!, currentItem.secret!!))
                .into(holder!!.ivFlikrImage)
    }

    fun replaceData(it: List<Photo>) {
        items = it
        notifyDataSetChanged()
    }

}