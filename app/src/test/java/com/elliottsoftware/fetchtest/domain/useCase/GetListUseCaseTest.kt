package com.elliottsoftware.fetchtest.domain.useCase

import com.elliottsoftware.fetchtest.data.FakeDataSource
import com.elliottsoftware.fetchtest.data.models.FetchItem
import com.elliottsoftware.fetchtest.domain.models.UIResponse
import com.elliottsoftware.fetchtest.domain.repositories.RemoteRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import java.lang.Exception

class GetListUseCaseTest() {
    val expectedItemList = listOf(FetchItem(id=0, listId = 0, name = "0"),)

    @Test
    fun getListTest() = runTest {
        val fakeDataSource: RemoteRepository = FakeDataSource()
        val items = fakeDataSource.getList().toList()

        Assert.assertEquals(UIResponse.Success(expectedItemList), items[1])
       Assert.assertEquals(UIResponse.Loading, items[0])
    }
    @Test
    fun getListTestException() = runTest {

        val fakeDataSource: RemoteRepository = FakeDataSource(throwError = true)
        val items = fakeDataSource.getList().toList()

        Assert.assertEquals(UIResponse.Loading, items[0])
        //weird equality bug with exceptions that is fixed by calling toString()
        Assert.assertEquals(UIResponse.Failure(Exception()).toString(), items[1].toString())
    }
}