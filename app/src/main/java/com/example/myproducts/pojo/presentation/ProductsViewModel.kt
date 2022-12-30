package com.example.myproducts.pojo.presentation

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
    val products : LiveData<List<Products>> get() = _products

    private fun getProductsList() = viewModelScope.launch{
       var response = remoteRepositoryImp.getAPIProducts()
        if(response.isSuccessful && response.body() != null){
            _products.postValue(response.body())
        }
    }
}