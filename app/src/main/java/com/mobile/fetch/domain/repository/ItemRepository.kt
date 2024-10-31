package com.mobile.fetch.domain.repository

import com.mobile.fetch.domain.model.ItemDomain
import com.mobile.fetch.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ItemRepository {

    suspend fun getItems(): Flow<NetworkResult<List<ItemDomain>>>
}