package com.elliottsoftware.fetchtest.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.elliottsoftware.fetchtest.data.models.FetchItem
import com.elliottsoftware.fetchtest.data.remote.FetchApi
import com.elliottsoftware.fetchtest.data.remote.RetrofitInstance
import kotlin.math.max

// The initial key used for loading.
// This is the FetchItem id of the first article that will be loaded
private const val STARTING_KEY = 0

//todo: I thing for us the Int is going to be the itemId of the fethcItem
class FetchItemPagingSource(
    val api: FetchApi = RetrofitInstance.api
):PagingSource<Int, FetchItem>() {
    // The refresh key is used for the initial load of the next PagingSource, after invalidation
    override fun getRefreshKey(state: PagingState<Int, FetchItem>): Int? {
        // In our case we grab the item closest to the anchor position
        // then return its id - (state.config.pageSize / 2) as a buffer
        val anchorPosition = state.anchorPosition ?: return null
        val article = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = article.id - (state.config.pageSize / 2))
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FetchItem> {
        // Start paging with the STARTING_KEY if this is the first load
        val start = params.key ?: STARTING_KEY
        // Load as many items as hinted by params.loadSize
        val range = start.until(start + params.loadSize)
        return try{
            val body = api.getPosts().body()!!
            val nonNullList = sortBlankNull(body)
            val list = sortById(nonNullList)
            LoadResult.Page(
                data = list,
                // Make sure we don't try to load items behind the STARTING_KEY
                prevKey = when (start) {
                    STARTING_KEY -> null
                    else -> ensureValidKey(key = range.first - params.loadSize)
                },
                nextKey = range.last + 1
            )

        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }

    /**
     * Makes sure the paging key is never less than [STARTING_KEY]
     */
    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)

    private fun sortBlankNull(list:List<FetchItem>):List<FetchItem>{
        val data = list.filter {  !it.name.isNullOrBlank() }
        return data
    }
    //todo: THIS IS THE SAME AS SORT BY NAME
    private fun sortById(list:List<FetchItem>):List<FetchItem>{
        val data = list.sortedBy {  it.id }
        return data
    }
}