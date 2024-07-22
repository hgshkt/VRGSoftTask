package com.hgshkt.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.hgshkt.data.local.image.LocalImageStorage
import com.hgshkt.data.mapper.toDomain
import com.hgshkt.data.paging.publication.PublicationsPagingSource
import com.hgshkt.data.paging.publication.pagingConfig
import com.hgshkt.domain.data.PublicationRepository
import com.hgshkt.domain.model.Publication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class PublicationRepositoryImpl(
    private val pagingSource: PublicationsPagingSource,
    private val localImageStorage: LocalImageStorage
) : PublicationRepository {
    override fun getPublications(): Flow<PagingData<Publication>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                pagingSource
            }
        ).flow
            .map { pagingData ->
                pagingData.map { childrenData ->
                    CoroutineScope(Dispatchers.IO).launch {
                        localImageStorage.save(childrenData.childrenData?.thumbnail)
                    }
                    childrenData.toDomain()
                }
            }
    }
}