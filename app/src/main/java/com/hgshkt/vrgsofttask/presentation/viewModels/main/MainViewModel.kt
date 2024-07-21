package com.hgshkt.vrgsofttask.presentation.viewModels.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.hgshkt.domain.usecases.GetPublicationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getPublicationsUseCase: GetPublicationsUseCase
): ViewModel() {
    val publicationsPagingData = getPublicationsUseCase.execute().cachedIn(viewModelScope)
}