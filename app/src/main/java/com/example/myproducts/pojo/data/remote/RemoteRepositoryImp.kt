package com.example.myproducts.pojo.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteRepositoryImp(private val api: ServiceAPI) : RemoteRepository {
    override suspend fun getAPIProducts() = withContext(Dispatchers.IO) {
        api.getAPIProducts()
    }
}