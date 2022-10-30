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


private const val ITEMS_PER_PAGE = 20

data class ListViewUIState(
    val listData:UIResponse<List<FetchItem>> = UIResponse.Failure(Exception())
)

@HiltViewModel
class ListViewModel
    @Inject constructor(
    val remoteRepository: RemoteRepository ,
   // val repository: TestFetchItemRepository = TestFetchItemRepository()

): ViewModel() {


    var state = mutableStateOf(ListViewUIState())
//    val items= Pager(
//        config = PagingConfig(pageSize = ITEMS_PER_PAGE),
//        pagingSourceFactory = { repository.fetchItemPagingSource() }
//    ) .flow
//        // cachedIn allows paging to remain active in the viewModel scope, so even if the UI
//        // showing the paged data goes through lifecycle changes, pagination remains cached and
//        // the UI does not have to start paging from the beginning when it resumes.
//        .cachedIn(GlobalScope)


//    init{
//        getList()
//    }

    fun getList() = viewModelScope.launch{
        remoteRepository.getList().collect{ list ->
            state.value = state.value.copy(listData = list)
        }
    }
}