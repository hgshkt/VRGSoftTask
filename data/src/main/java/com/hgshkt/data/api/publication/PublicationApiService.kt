package com.hgshkt.data.api.publication

import com.hgshkt.data.api.publication.model.PublicationPageJson
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface PublicationApiService {
    @GET("/top.json")
    fun top(
        @Query("after") after: String?,
        @Query("count") count: Int
    ): Response<PublicationPageJson>
}