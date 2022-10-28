package com.elliottsoftware.fetchtest.data

import com.elliottsoftware.fetchtest.data.models.FetchItem
import com.elliottsoftware.fetchtest.domain.models.UIResponse
import com.elliottsoftware.fetchtest.domain.repositories.RemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDataSource(
    returnedData:FetchItem = FetchItem(1,1,"1")
    ,throwError:Boolean = false
): RemoteRepository{

    private var error:Boolean = throwError

     val fetchItemsList = listOf<FetchItem>(
         returnedData
    )

    //we make it deterministic by having the function always emit the same data
    override suspend fun getList()= flow{
        try{
            emit(UIResponse.Loading)
            if(error){
                throw Exception()
            }
            emit(UIResponse.Success(fetchItemsList))
        }catch (e:Exception){
            emit(UIResponse.Failure(e))
        }

    }


}