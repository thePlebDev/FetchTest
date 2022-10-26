package com.elliottsoftware.fetchtest.data.repositories

import com.elliottsoftware.fetchtest.data.models.FetchItem
import com.elliottsoftware.fetchtest.data.remote.FetchApi
import com.elliottsoftware.fetchtest.data.remote.RetrofitInstance
import com.elliottsoftware.fetchtest.domain.models.UIResponse
import com.elliottsoftware.fetchtest.domain.repositories.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteRepositoryImpl(
    val api: FetchApi = RetrofitInstance.api
):RemoteRepository {
    override suspend fun getList() = flow {
        try{
            emit(UIResponse.Loading)
            val body = api.getPosts().body()!!
            val list = sortBlankNull(body)
            emit(UIResponse.Success(list))
        }catch (e:Exception){
            emit(UIResponse.Failure(e))
        }
    }

    private fun sortBlankNull(list:List<FetchItem>):List<FetchItem>{
        val data = list.filter {  !it.name.isNullOrBlank() }
        return data
    }
}