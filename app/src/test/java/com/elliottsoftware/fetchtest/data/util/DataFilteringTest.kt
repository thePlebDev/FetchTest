package com.elliottsoftware.fetchtest.data.util


import com.elliottsoftware.fetchtest.data.models.FetchItem
import org.junit.Assert
import org.junit.Test

class DataFilteringTest {
    //TEST NULL FILTERS
    //TEST LISTID FILTER
    //TEST SORT BY LISTID THEN NAME FILTER

    @Test
    fun testing(){
        val fetchList:List<FetchItem> = listOf(
            FetchItem(500,1,"Item 500"),

            FetchItem(200,1,"Item 200"),

            FetchItem(500,2,"Item 500"),

            FetchItem(200,2,"Item 200"),
        )
//        val sortedList = fetchList.groupBy { it.listId }
//            .map{item -> item.value.sortedBy { it.id }}
//        //val expectedList = merge(sortedList[0], sortedList)
//        val list: MutableList<FetchItem> = ArrayList()
//        list.addAll(sortedList[0])
//        list.addAll(sortedList[1])

        val sortedList = fetchList.groupBy { it.listId }.map{item -> item.value.sortedBy { it.id }}
        val numbers = listOf(5, 2, 10, 4)

       // val simpleSum = sortedList.reduce { sum, element -> sum + element }

        Assert.assertEquals(sortedList,1)
    }
}