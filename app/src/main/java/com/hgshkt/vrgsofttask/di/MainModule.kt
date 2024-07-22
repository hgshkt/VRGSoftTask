package com.hgshkt.vrgsofttask.di

import android.content.Context
import com.hgshkt.data.api.RetrofitService
import com.hgshkt.data.api.publication.PublicationApiService
import com.hgshkt.data.local.image.LocalImageStorage
import com.hgshkt.data.local.image.LocalImageStorageImpl
import com.hgshkt.data.paging.publication.PublicationsPagingSource
import com.hgshkt.data.repository.PublicationRepositoryImpl
import com.hgshkt.domain.data.PublicationRepository
import com.hgshkt.domain.usecases.GetPublicationsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        pagingSource: PublicationsPagingSource,
        localImageStorage: LocalImageStorage
    ): PublicationRepositoryImpl {
        return PublicationRepositoryImpl(pagingSource, localImageStorage)
    }

    @Provides
    @Singleton
    fun provideLocalImageStorageImpl(
        @ApplicationContext context: Context
    ): LocalImageStorageImpl {
        return LocalImageStorageImpl(context)
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

    @Binds
    @Singleton
    abstract fun provideLocalImageStorage(
        publicationRepositoryImpl: LocalImageStorageImpl
    ): LocalImageStorage
}