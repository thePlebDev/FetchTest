package com.elliottsoftware.fetchtest.data.repositories

import android.util.Log
import com.elliottsoftware.fetchtest.data.models.FetchItem
import com.elliottsoftware.fetchtest.data.remote.FetchApi
import com.elliottsoftware.fetchtest.data.util.DataFiltering
import com.elliottsoftware.fetchtest.domain.models.UIResponse
import com.elliottsoftware.fetchtest.domain.repositories.RemoteRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteRepositoryImpl
    @Inject constructor(
    val api: FetchApi
):RemoteRepository {
    override suspend fun getList() = flow {
        try{
            emit(UIResponse.Loading)
            val body = api.getPosts().body()!!
           val nonNullData = DataFiltering.sortBlankNull(body)
            val listIdData = DataFiltering.sortByListId(nonNullData)
            val sortedByName = DataFiltering.sortByName(listIdData)

            emit(UIResponse.Success(sortedByName))
        }catch (e:Exception){
            Log.e("RemoteRepositoryImplError",e.message.toString())
            emit(UIResponse.Failure(e))
        }
    }


}