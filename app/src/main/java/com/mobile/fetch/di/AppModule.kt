package com.mobile.fetch.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mobile.fetch.data.network.service.ItemService
import com.mobile.fetch.data.repository.ItemRepositoryImpl
import com.mobile.fetch.domain.repository.ItemRepository
import com.mobile.fetch.ui.viewmodel.ItemViewModel
import com.mobile.fetch.utils.Constants.BASE_URL
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit

val appModule = module {

    single<CoroutineDispatcher> {
        Dispatchers.IO
    }

    single<OkHttpClient> {
        OkHttpClient.Builder().readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS).retryOnConnectionFailure(true).build()
    }


    single<Retrofit> {
        val contentType = "application/json".toMediaType()

        Retrofit.Builder().baseUrl(BASE_URL).client(get())
            .addConverterFactory(Json.asConverterFactory(contentType)).build()
    }

    single<ItemService> {
        val retrofit = get<Retrofit>()
        retrofit.create()
    }

    single<ItemRepository> {
        ItemRepositoryImpl(
            itemService = get(), ioDispatcher = get()
        )
    }

    viewModelOf(::ItemViewModel)
}