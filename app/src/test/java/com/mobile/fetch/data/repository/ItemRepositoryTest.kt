package com.mobile.fetch.data.repository

import com.mobile.fetch.TestData.itemDomain
import com.mobile.fetch.domain.repository.ItemRepository
import com.mobile.fetch.utils.NetworkResult
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ItemRepositoryTest {

    private val itemRepository: ItemRepository = mockk()

    @Test
    fun `getItems() return success result`() = runTest {
        coEvery {
            itemRepository.getItems()
        } returns flowOf(NetworkResult.Success(listOf(itemDomain)))

        val result = itemRepository.getItems().first()

        assertEquals(NetworkResult.Success(listOf(itemDomain)), result)
    }
}