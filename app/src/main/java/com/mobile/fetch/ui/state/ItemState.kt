package com.mobile.fetch.ui.state

import com.mobile.fetch.domain.model.ItemDomain

data class ItemState(
    val isLoading: Boolean = false,
    val items: Map<Int, List<ItemDomain>>? = null,
    val errorMessage: String? = null
)
