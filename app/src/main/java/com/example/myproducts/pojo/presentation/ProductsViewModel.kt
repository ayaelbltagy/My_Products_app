package com.example.myproducts.pojo.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myproducts.pojo.data.remote.RemoteRepositoryImp
import com.example.myproducts.pojo.data.remote.RetrofitBuilder
import com.example.myproducts.pojo.data.remote.ServiceAPI
import com.example.myproducts.pojo.models.Products
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {
    private var remoteRepositoryImp: RemoteRepositoryImp

    init {
        var serviceAPIInstance = RetrofitBuilder.getRetrofitBuilder().create(ServiceAPI::class.java)
        remoteRepositoryImp = RemoteRepositoryImp(serviceAPIInstance)
        getProductsList()
    }

    private var _products = MutableLiveData<List<Products>>()
    val products: LiveData<List<Products>> get() = _products

    // to handle navigation
    private var _navigateToSelectedProperty = MutableLiveData<Products>()
    val navigateToSelectedProperty: LiveData<Products> get() = _navigateToSelectedProperty


      fun getProductsList() = viewModelScope.launch {
          try {
              var response = remoteRepositoryImp.getAPIProducts()
              if (response.isSuccessful && response.body() != null) {
                  _products.postValue(response.body())
              }
          }
          catch (ex: Exception) {
              // in case failed connection or server error
              Log.i("testsize","test")
              _products.value = emptyList()
          }

    }

    fun displayPropertyDetails(product: Products) {
        _navigateToSelectedProperty.value = product
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }


}