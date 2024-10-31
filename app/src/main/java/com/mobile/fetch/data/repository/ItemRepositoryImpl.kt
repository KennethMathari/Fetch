package com.mobile.fetch.data.repository

import com.mobile.fetch.data.mapper.toItemDomain
import com.mobile.fetch.data.network.service.ItemService
import com.mobile.fetch.domain.model.ItemDomain
import com.mobile.fetch.domain.repository.ItemRepository
import com.mobile.fetch.utils.NetworkResult
import com.mobile.fetch.utils.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ItemRepositoryImpl(
    private val itemService: ItemService,
    private val ioDispatcher: CoroutineDispatcher
): ItemRepository{

    override suspend fun getItems(): Flow<NetworkResult<List<ItemDomain>>>{
        return flow {
            val result = safeApiCall {
                itemService.getItems().map { itemDTO ->
                        itemDTO.toItemDomain()
                }
            }
            emit(result)
        }.flowOn(ioDispatcher)
    }
}