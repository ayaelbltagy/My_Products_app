package com.example.myproducts.pojo.presentation

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.myproducts.R
import com.example.myproducts.databinding.FragmentProductDetailsBinding
import com.example.myproducts.databinding.MainFragmentBinding
import kotlinx.coroutines.runBlocking


class ProductDetailsFragment : Fragment() {
    private lateinit var viewModel: ProductsViewModel
    private lateinit var binding: FragmentProductDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         // Inflate the layout for this fragment
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
       // return inflater.inflate(R.layout.fragment_product_details, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)
        val product = ProductDetailsFragmentArgs.fromBundle(requireArguments()).oneProduct
        activity?.title = product.title
        binding.title.text = product.title
        binding.price.text = product.price.toString()
        binding.desc.text = product.description
        binding.ratingBar.rating = product.rating.rate!!.toFloat()
        Glide.with(this@ProductDetailsFragment)
            .load(product.image)
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
            .into(binding.imageView)
    }


}