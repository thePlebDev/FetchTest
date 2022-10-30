package com.elliottsoftware.fetchtest.presentation.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.elliottsoftware.fetchtest.data.models.FetchItem
import com.elliottsoftware.fetchtest.data.repositories.TestFetchItemRepository
import com.elliottsoftware.fetchtest.domain.models.UIResponse
import com.elliottsoftware.fetchtest.domain.repositories.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


data class ListViewUIState(
    val listData:UIResponse<List<FetchItem>> = UIResponse.Loading
)

@HiltViewModel
class ListViewModel
    @Inject constructor(
    val remoteRepository: RemoteRepository ,


): ViewModel() {

    var state = mutableStateOf(ListViewUIState())



    init{
        getList()
    }

    fun getList() = viewModelScope.launch{
        remoteRepository.getList().collect{ list ->
            state.value = state.value.copy(listData = list)
        }
    }
}