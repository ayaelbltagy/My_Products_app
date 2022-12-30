package com.example.myproducts.pojo.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myproducts.databinding.MainFragmentBinding
import androidx.recyclerview.widget.GridLayoutManager




class ProductsFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {

        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)
        // show dialog till api get response
        binding.statusLoadingWheel.visibility = View.VISIBLE
        binding.productsRecycler.layoutManager = GridLayoutManager(context, 2)
        viewModel.products.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
        // hide dialog as list is ready
                binding.statusLoadingWheel.visibility = View.GONE
                binding.productsRecycler.adapter = ProductsAdapter(this@ProductsFragment, it)
            }
        })

    }

}