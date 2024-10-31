package com.mobile.fetch.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.fetch.domain.repository.ItemRepository
import com.mobile.fetch.ui.state.ItemState
import com.mobile.fetch.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ItemViewModel(
    private val itemRepository: ItemRepository
) : ViewModel() {

    private val _itemState = MutableStateFlow(ItemState())
    val itemState: StateFlow<ItemState> get() = _itemState.asStateFlow()

    init {
        getItems()
    }

    private fun getItems() {
        viewModelScope.launch {
            _itemState.value = ItemState(
                isLoading = true, items = null, errorMessage = null
            )

            itemRepository.getItems().collect { result ->
                when (result) {
                    is NetworkResult.ClientError -> {
                        updateErrorMessage("Unable to Fetch Items! Please Try Again.")
                    }

                    is NetworkResult.NetworkError -> {
                        updateErrorMessage("Unable to Fetch Items! Check Internet Connection")
                    }

                    is NetworkResult.ServerError -> {
                        updateErrorMessage("Oops! Our Server is Down.")
                    }

                    is NetworkResult.Success -> {
                        _itemState.value =
                            ItemState(items = result.data.filter { it.name.isNotBlank() }
                                .sortedWith(compareBy({ it.listId }, { it.name }))
                                .groupBy { it.listId }, isLoading = false, errorMessage = null)
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(errorMessage: String) {
        _itemState.value = ItemState(
            isLoading = false, items = null, errorMessage = errorMessage
        )
    }
}