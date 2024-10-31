package com.mobile.fetch.data.mapper

import com.mobile.fetch.data.network.model.ItemDTO
import com.mobile.fetch.domain.model.ItemDomain

fun ItemDTO.toItemDomain(): ItemDomain {
    return ItemDomain(
        id = this.id, listId = this.listId, name = this.name ?: ""
    )
}