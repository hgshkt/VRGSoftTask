package com.hgshkt.data.paging.publication


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hgshkt.data.api.publication.PublicationApiService
import com.hgshkt.data.api.publication.model.PublicationPageJson.Data.Children
import retrofit2.HttpException

class PublicationsPagingSource(
    private val apiService: PublicationApiService
) : PagingSource<String, Children>() {

    private val count = pagingConfig.pageSize

    override fun getRefreshKey(state: PagingState<String, Children>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey ?: anchorPage?.nextKey
        }
    }

    override suspend fun load(
        params: LoadParams<String>
    ): LoadResult<String, Children> {
        try {
            val response = apiService.top(
                count = count,
                after = params.key
            )

            if (response.isSuccessful) {
                val body = response.body()!!

                val publications = body.data!!.children!!

                val prevKey = params.key

                val nextKey = body.data.after

                return LoadResult.Page(publications, prevKey, nextKey)
            } else {
                return LoadResult.Error(HttpException(response))
            }

        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}