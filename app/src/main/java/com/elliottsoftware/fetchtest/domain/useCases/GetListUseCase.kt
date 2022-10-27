package com.elliottsoftware.fetchtest.domain.useCases

import com.elliottsoftware.fetchtest.data.models.FetchItem
import com.elliottsoftware.fetchtest.data.repositories.RemoteRepositoryImpl
import com.elliottsoftware.fetchtest.domain.models.UIResponse
import com.elliottsoftware.fetchtest.domain.repositories.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetListUseCase
    @Inject constructor(
    val remoteRepository: RemoteRepository
) {

    suspend operator fun invoke(): Flow<UIResponse<List<FetchItem>>> = remoteRepository.getList()
}