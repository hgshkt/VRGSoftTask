package com.hgshkt.vrgsofttask.presentation.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.hgshkt.domain.model.Publication
import com.hgshkt.vrgsofttask.presentation.viewModels.main.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    val publications = viewModel.publicationsPagingData.collectAsLazyPagingItems()

    PublicationList(publications)
}

@Composable
private fun PublicationList(
    publicationsLazyItems: LazyPagingItems<Publication>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            count = publicationsLazyItems.itemCount,
            key = publicationsLazyItems.itemKey { it.id },
            contentType = publicationsLazyItems.itemContentType { "Publications" }
        ) { index ->
            publicationsLazyItems[index]?.let { publication ->
                PublicationItem(publication)
            }
        }
    }
}

@Composable
private fun PublicationItem(publication: Publication) {
    TODO("Not yet implemented")
}
