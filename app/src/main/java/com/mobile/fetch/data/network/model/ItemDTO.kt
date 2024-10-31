package com.mobile.fetch.data.network.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ItemDTO(
    @SerialName("id")
    val id: Int,
    @SerialName("listId")
    val listId: Int,
    @SerialName("name")
    val name: String?
)