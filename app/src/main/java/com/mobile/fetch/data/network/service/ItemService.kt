package com.mobile.fetch.data.network.service

import com.mobile.fetch.data.network.model.ItemDTO
import retrofit2.http.GET

interface ItemService {

    @GET("/hiring.json")
    suspend fun getItems(): List<ItemDTO>

}