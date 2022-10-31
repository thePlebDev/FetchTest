package com.elliottsoftware.fetchtest.data.util

import com.elliottsoftware.fetchtest.data.models.FetchItem

class DataFiltering {

    companion object {
        fun sortBlankNull(list:List<FetchItem>):List<FetchItem>{
            val data = list.filter {  !it.name.isNullOrBlank() }
            return data
        }
        //todo: THIS IS THE SAME AS SORT BY NAME
         fun sortByListId(list:List<FetchItem>):List<FetchItem>{
            val data = list.sortedBy {  it.listId }
            return data
        }

        //todo: VERY SPAGHETTI BUT IT WORKS
        fun sortByName(list:List<FetchItem>):List<FetchItem>{
            val data = list.groupBy { it.listId }
                .map{item -> item.value.sortedBy { it.id }}
            //val expectedList = merge(sortedList[0], sortedList)
            val mutableList: MutableList<FetchItem> = ArrayList()
            mutableList.addAll(data[0])
            mutableList.addAll(data[1])
            mutableList.addAll(data[2])
            mutableList.addAll(data[3])
            return mutableList
        }
    }


}