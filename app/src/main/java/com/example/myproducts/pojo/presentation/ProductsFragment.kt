package com.example.myproducts.pojo.presentation

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myproducts.databinding.MainFragmentBinding
import com.google.android.material.snackbar.Snackbar


class ProductsFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: ProductsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.title = "Products List"
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductsViewModel::class.java)
        // setup my adapter
        var adapter = ProductsAdapter(AdapterClickListener {
            viewModel.displayPropertyDetails(it)
        })
        // show dialog till api get response
        binding.statusLoadingWheel.visibility = View.VISIBLE
        binding.productsRecycler.layoutManager = GridLayoutManager(context, 2)
        binding.productsRecycler.adapter = adapter
        viewModel.products.observe(viewLifecycleOwner, Observer {
            binding.statusLoadingWheel.visibility = View.GONE
            if (!it.isNullOrEmpty()) {
                // hide dialog as list is ready
                adapter.submitList(it)
            } else {
                Snackbar.make(
                    binding.constraintLayout,
                    "Failed to load products",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })
        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                // navigate to details
                this.findNavController().navigate(
                    ProductsFragmentDirections.actionProductsFragmentToProductDetailsFragment(it)
                )
                viewModel.displayPropertyDetailsComplete()
            }
        })

      // refresh
        binding.refreshLayout.setOnRefreshListener {
            binding.refreshLayout.isRefreshing = false
            viewModel.products.observe(viewLifecycleOwner, Observer {
                binding.statusLoadingWheel.visibility = View.GONE
                if (!it.isNullOrEmpty()) {
                    // hide dialog as list is ready
                    adapter.submitList(it)
                } else {
                    Snackbar.make(
                        binding.constraintLayout,
                        "Failed to load products",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            })

        }

    }

    override fun onResume() {
        super.onResume()
        // to handle back button press
        requireView().isFocusableInTouchMode = true
        requireView().requestFocus()
        requireView().setOnKeyListener { v: View?, keyCode: Int, event: KeyEvent ->
            activity?.finish()
            false
        }

    }
}