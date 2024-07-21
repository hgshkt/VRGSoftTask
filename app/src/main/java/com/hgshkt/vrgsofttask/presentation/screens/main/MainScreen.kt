package com.hgshkt.vrgsofttask.presentation.screens.main

import android.text.format.DateUtils
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.hgshkt.domain.model.Publication
import com.hgshkt.vrgsofttask.presentation.viewModels.main.MainViewModel
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
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
private fun PublicationItem(
    publication: Publication,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Author(publication.author)
        PublishDate(publication.date)
        AsyncImage(
            model = publication.imageUrl,
            contentDescription = "Publication image",
        )
        CommentariesCount(publication.commentariesCount)
    }
}

@Composable
fun CommentariesCount(commentariesCount: Int) {
    Text("$commentariesCount comments")
}

@Composable
fun PublishDate(date: Int) {
    Text(date.toFormat())
}

private fun Int.toFormat(): String {
    val currentTime = System.currentTimeMillis()
    val timeDiff = currentTime - (this * 1000)
    return DateUtils.getRelativeTimeSpanString(
        currentTime - timeDiff,
        currentTime,
        DateUtils.MINUTE_IN_MILLIS
    ).toString()
}

@Composable
private fun Author(author: String) {
    Text(author)
}
