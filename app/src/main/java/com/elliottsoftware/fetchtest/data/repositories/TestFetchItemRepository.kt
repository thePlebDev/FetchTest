package com.elliottsoftware.fetchtest.data.repositories

import com.elliottsoftware.fetchtest.data.models.FetchItem
import com.elliottsoftware.fetchtest.data.pagination.FetchItemPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class TestFetchItemRepository {

   fun fetchItemPagingSource() = FetchItemPagingSource()
}