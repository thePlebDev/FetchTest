package com.elliottsoftware.fetchtest.domain.repositories

import com.elliottsoftware.fetchtest.data.models.FetchItem
import com.elliottsoftware.fetchtest.domain.models.UIResponse
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {

    suspend fun getList():Flow<UIResponse<List<FetchItem>>>
}