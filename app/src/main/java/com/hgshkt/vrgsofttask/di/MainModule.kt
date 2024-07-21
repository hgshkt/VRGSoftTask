package com.hgshkt.vrgsofttask.di

import com.hgshkt.data.api.RetrofitService
import com.hgshkt.data.api.publication.PublicationApiService
import com.hgshkt.data.paging.publication.PublicationsPagingSource
import com.hgshkt.data.repository.PublicationRepositoryImpl
import com.hgshkt.domain.data.PublicationRepository
import com.hgshkt.domain.usecases.GetPublicationsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModuleImpl {
    @Provides
    @Singleton
    fun provideGetTopPublicationsUseCase(
        publicationRepository: PublicationRepository
    ): GetPublicationsUseCase {
        return GetPublicationsUseCase(publicationRepository)
    }

    @Provides
    @Singleton
    fun providePublicationRepositoryImpl(
        pagingSource: PublicationsPagingSource
    ): PublicationRepositoryImpl {
        return PublicationRepositoryImpl(pagingSource)
    }

    @Provides
    @Singleton
    fun providePublicationsPagingSource(
        api: PublicationApiService
    ): PublicationsPagingSource {
        return PublicationsPagingSource(api)
    }

    @Provides
    @Singleton
    fun providePublicationApiService(): PublicationApiService {
        return RetrofitService.retrofit.create(PublicationApiService::class.java)
    }
}
@Module
@InstallIn(SingletonComponent::class)
abstract class MainModule {
    @Binds
    @Singleton
    abstract fun providePublicationRepository(
        publicationRepositoryImpl: PublicationRepositoryImpl
    ): PublicationRepository
}