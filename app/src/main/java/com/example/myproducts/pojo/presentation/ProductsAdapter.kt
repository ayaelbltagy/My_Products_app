package com.example.myproducts.pojo.presentation

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.myproducts.R
import com.example.myproducts.databinding.ProductItemBinding
import com.example.myproducts.pojo.models.Products
import java.util.*

class ProductsAdapter() : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    private var productsList: List<Products> = ArrayList()
    private lateinit var fragment: ProductsFragment


    constructor(fragment: ProductsFragment,productsList: List<Products>) : this() {
        this.productsList = productsList
        this.fragment = fragment
    }


    inner class ViewHolder(@NonNull val itemBinding: ProductItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        init {
            // one item click
            itemBinding.root.setOnClickListener {
                findNavController(fragment).navigate(R.id.action_productsFragment_to_productDetailsFragment)

            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.itemBinding.title.text = productsList.get(position).title
        holder.itemBinding.price.text = productsList.get(position).price.toString()
        holder.itemBinding.ratingBar.rating = productsList.get(position).rating.rate!!.toFloat()

        Glide.with(fragment)
            .load(productsList.get(position).image)
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


    override fun getItemCount(): Int {
        return  productsList.size
    }
}