package com.hgshkt.data.paging.publication

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hgshkt.data.api.publication.PublicationApiService
import com.hgshkt.data.api.publication.model.PublicationPageJson.Data.Children.ChildrenData

class PublicationsPagingSource(
    private val apiService: PublicationApiService
): PagingSource<Int, ChildrenData>() {
    override fun getRefreshKey(state: PagingState<Int, ChildrenData>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ChildrenData> {
        TODO("Not yet implemented")
    }
}