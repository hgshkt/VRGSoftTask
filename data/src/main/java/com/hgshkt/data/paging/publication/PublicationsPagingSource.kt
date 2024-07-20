package com.hgshkt.data.paging.publication


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hgshkt.data.api.publication.PublicationApiService
import com.hgshkt.data.api.publication.model.PublicationPageJson.Data.Children
import retrofit2.HttpException

class PublicationsPagingSource(
    private val apiService: PublicationApiService
) : PagingSource<PublicationKey, Children>() {

    private val count = pagingConfig.pageSize

    override fun getRefreshKey(state: PagingState<PublicationKey, Children>): PublicationKey? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        with(anchorPage) {
            return PublicationKey(
                after = nextKey?.current!!,
                current = prevKey?.after ?: nextKey?.before!!,
                before = prevKey?.current!!
            )
        }
    }

    override suspend fun load(
        params: LoadParams<PublicationKey>
    ): LoadResult<PublicationKey, Children> {
        try {
            val response = apiService.top(
                count = count,
                after = params.key?.after
            )

            if (response.isSuccessful) {
                val body = response.body()!!

                val publications = body.data!!.children!!

                val prevKey = PublicationKey(
                    after = params.key?.after!!,
                    current = params.key?.current!!,
                    before = params.key?.before!!,
                )

                val nextKey = PublicationKey(
                    current = body.data.after!!,
                    before = params.key?.after!!
                )

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

data class PublicationKey(
    val after: String? = null,
    val current: String? = null,
    val before: String? = null
)