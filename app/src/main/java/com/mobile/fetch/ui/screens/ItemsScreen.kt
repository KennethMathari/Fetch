package com.mobile.fetch.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mobile.fetch.domain.model.ItemDomain
import com.mobile.fetch.ui.component.Loading
import com.mobile.fetch.ui.viewmodel.ItemViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ItemsScreen(
    itemViewModel: ItemViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope
) {

    val itemState by itemViewModel.itemState.collectAsStateWithLifecycle()

    Box {
        if (itemState.isLoading) {
            Loading()
        } else {
            Column {
                LazyColumn(
                    modifier = modifier.fillMaxSize()
                ) {
                    items(itemState.items?.keys?.toList() ?: emptyList()) { listId ->
                        val items = itemState.items?.get(listId) ?: emptyList()
                        ListGroup(listId = listId, items = items)
                    }
                }
            }
        }
    }

    LaunchedEffect(itemState.errorMessage) {
        scope.launch {
            itemState.errorMessage?.let { errorMessage ->
                snackbarHostState.showSnackbar(errorMessage)
            }
        }

    }

}

@Composable
fun ListGroup(listId: Int, items: List<ItemDomain>) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(
            text = "List ID: $listId",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        items.forEach { item ->
            ItemRow(item = item)
        }
    }
}

@Composable
fun ItemRow(item: ItemDomain) {
    Text(
        text = "${item.name} (ID: ${item.id})",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(start = 16.dp, top = 2.dp)
    )
}