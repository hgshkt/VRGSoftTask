package com.hgshkt.domain.usecases

import com.hgshkt.domain.model.Publication
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingData
import com.hgshkt.domain.data.PublicationRepository

class GetPublicationsUseCase(
    private val publicationRepository: PublicationRepository
) {

    fun execute(): Flow<PagingData<Publication>> {
        return publicationRepository.getPublications()
    }
}