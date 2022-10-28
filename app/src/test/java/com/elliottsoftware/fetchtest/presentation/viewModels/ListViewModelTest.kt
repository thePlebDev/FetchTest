package com.elliottsoftware.fetchtest.presentation.viewModels

import com.elliottsoftware.fetchtest.data.FakeDataSource
import com.elliottsoftware.fetchtest.data.models.FetchItem
import com.elliottsoftware.fetchtest.domain.models.UIResponse
import com.elliottsoftware.fetchtest.domain.repositories.RemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class ListViewModelTest {

    //TODO: CHANGE THIS OVER TO COROUTINE RULE IN VERSION 2 OF APP
    val dispatcher = StandardTestDispatcher()
    private lateinit var viewModel: ListViewModel
    private lateinit var fakeDataSource: FakeDataSource


    @Before
    fun setup(){
        Dispatchers.setMain(dispatcher)
        fakeDataSource = FakeDataSource()
        viewModel = ListViewModel(fakeDataSource)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

//    val expectedItemList = listOf(FetchItem(id=0, listId = 0, name = "0"),)
//
    //TODO: FIND A BETTER WAY TO TEST, LOOK IN TO TURBINE LIBRARY. VERSION2 OF APP
    @Test
    fun getListTest() = runTest {
    Assert.assertEquals(UIResponse.Loading, viewModel.state.value.listData)
    delay(2000)
    Assert.assertEquals(UIResponse.Success(listOf(FetchItem(1,1,"1"))), viewModel.state.value.listData)


    }




}