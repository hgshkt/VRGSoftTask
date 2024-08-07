package com.hgshkt.domain.data

import androidx.paging.PagingData
import com.hgshkt.domain.model.Publication
import kotlinx.coroutines.flow.Flow

interface PublicationRepository {
    fun getPublications(): Flow<PagingData<Publication>>
}