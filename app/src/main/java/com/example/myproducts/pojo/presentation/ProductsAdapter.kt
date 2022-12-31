package com.example.myproducts.pojo.presentation

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.myproducts.databinding.ProductItemBinding
import com.example.myproducts.pojo.models.Products

class ProductsAdapter(val clickListener: AdapterClickListener) :
    ListAdapter<Products, ProductsAdapter.ViewHolder>(MainAdapterCallback()) {


    inner class ViewHolder(@NonNull val itemBinding: ProductItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {

        holder.itemBinding.ratingBar.rating = getItem(position).rating.rate!!.toFloat()
        holder.itemBinding.product = getItem(position)!!
        holder.itemBinding.clickListener = clickListener
        holder.itemBinding.executePendingBindings()

        Glide.with(holder.itemView)
            .load(getItem(position).image)
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .into(holder.itemBinding.imageView)
    }


}

class MainAdapterCallback : DiffUtil.ItemCallback<Products>() {
    override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
        return oldItem.id == newItem.id
    }
}

class AdapterClickListener(val clickListener: (oneProduct: Products) -> Unit) {
    fun onItemClick(oneProduct: Products) = clickListener(oneProduct)
}





