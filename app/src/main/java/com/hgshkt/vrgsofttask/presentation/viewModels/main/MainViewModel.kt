package com.hgshkt.vrgsofttask.presentation.viewModels.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hgshkt.domain.usecases.GetPublicationsUseCase

class MainViewModel(
    private val getPublicationsUseCase: GetPublicationsUseCase
): ViewModel() {
    val publicationsPagingData = getPublicationsUseCase.execute().cachedIn(viewModelScope)
}