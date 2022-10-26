package com.elliottsoftware.fetchtest.presentation.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elliottsoftware.fetchtest.data.models.FetchItem
import com.elliottsoftware.fetchtest.domain.models.UIResponse
import com.elliottsoftware.fetchtest.domain.useCases.GetListUseCase
import kotlinx.coroutines.launch

private val calfList = listOf<String>("fdaf","fdsaf","fdsafdtre","another","moreTest","Another one")

data class ListViewUIState(
    val listData:UIResponse<List<FetchItem>> = UIResponse.Loading
)

class ListViewModel(
    val getListUseCase: GetListUseCase = GetListUseCase()

): ViewModel() {


    var state = mutableStateOf(ListViewUIState())
    init{
        getList()
    }

    fun getList() = viewModelScope.launch{
        getListUseCase().collect{ list ->
            state.value = state.value.copy(listData = list)
        }
    }
}