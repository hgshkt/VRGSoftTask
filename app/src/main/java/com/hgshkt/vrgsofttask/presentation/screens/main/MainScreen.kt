package com.hgshkt.vrgsofttask.presentation.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.hgshkt.domain.model.Publication
import com.hgshkt.vrgsofttask.presentation.data.hoursAgo
import com.hgshkt.vrgsofttask.presentation.navigation.Screen
import com.hgshkt.vrgsofttask.presentation.viewModels.main.MainViewModel

@Composable
fun MainScreen(
    controller: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val publications = viewModel.publicationsPagingData.collectAsLazyPagingItems()

    PublicationList(publications) { url ->
        controller.navigate(Screen.Image.setValueToRoute(url))
    }
}

@Composable
private fun PublicationList(
    publicationsLazyItems: LazyPagingItems<Publication>,
    onImageClick: (url: String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
    ) {
        items(
            count = publicationsLazyItems.itemCount,
            key = publicationsLazyItems.itemKey { it.id },
            contentType = publicationsLazyItems.itemContentType { "Publications" }
        ) { index ->
            publicationsLazyItems[index]?.let { publication ->
                PublicationItem(
                    publication = publication,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 4.dp),
                    onImageClick = { onImageClick(publication.imageUrl) }
                )
            }
        }
    }
}

@Composable
private fun PublicationItem(
    publication: Publication,
    modifier: Modifier = Modifier,
    onImageClick: () -> Unit
) {
    Card(modifier) {
        Column(
            modifier = Modifier.padding(8.dp, 4.dp)
        ) {
            PublishDate(publication.date, modifier = Modifier.padding(4.dp))
            Author(publication.author, modifier = Modifier.padding(4.dp))
            AsyncImage(
                model = publication.imageUrl,
                contentDescription = "Publication image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onImageClick()
                    }
            )
            CommentariesCount(
                publication.commentariesCount,
                modifier = Modifier.padding(8.dp, 8.dp, 14.dp)
            )
        }
    }
}

@Composable
fun CommentariesCount(commentariesCount: Int, modifier: Modifier = Modifier) {
    Text("$commentariesCount comments", modifier = modifier)
}

@Composable
fun PublishDate(date: Int, modifier: Modifier = Modifier) {
    Text(date.hoursAgo, modifier = modifier)
}

@Composable
private fun Author(author: String, modifier: Modifier = Modifier) {
    Text(author, fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = modifier)
}
