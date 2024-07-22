package com.hgshkt.vrgsofttask.presentation.screens.main

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.hgshkt.domain.model.Publication
import com.hgshkt.vrgsofttask.presentation.viewModels.main.MainViewModel

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
        modifier = Modifier
    ) {
        items(
            count = publicationsLazyItems.itemCount,
            key = publicationsLazyItems.itemKey { it.id },
            contentType = publicationsLazyItems.itemContentType { "Publications" }
        ) { index ->
            publicationsLazyItems[index]?.let { publication ->
                PublicationItem(publication, modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 4.dp)
                )
            }
        }
    }
}

@Composable
private fun PublicationItem(
    publication: Publication,
    modifier: Modifier = Modifier
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
                modifier = Modifier.fillMaxSize()
            )
            CommentariesCount(
                publication.commentariesCount,
                modifier = Modifier.padding(8.dp, 8.dp, 14.dp)
            )
        }
    }
}

@Preview
@Composable
private fun ItemPreview() {
    val publication = Publication(
        id = "t2_qwer",
        author = "author",
        date = 1721566733,
        imageUrl = "https://cdn.britannica.com/34/235834-050-C5843610/two-different-breeds-of-cats-side-by-side-outdoors-in-the-garden.jpg",
        commentariesCount = 14
    )
    PublicationItem(
        publication = publication
    )
}

@Composable
fun CommentariesCount(commentariesCount: Int, modifier: Modifier = Modifier) {
    Text("$commentariesCount comments", modifier = modifier)
}

@Composable
fun PublishDate(date: Int, modifier: Modifier = Modifier) {
    Text(date.toFormat(), modifier = modifier)
}

private fun Int.toFormat(): String {
    val now = System.currentTimeMillis() / 1000
    val seconds = now - this

    val minutes = seconds / 60
    val hours = minutes / 60
    val days = hours / 24
    val weeks = days / 7
    val years = days / 356

    return when {
        years > 0 -> "$years day${if (years > 1) "s" else ""} ago"
        weeks > 0 -> "$weeks day${if (weeks > 1) "s" else ""} ago"
        days > 0 -> "$days day${if (days > 1) "s" else ""} ago"
        hours > 0 -> "$hours hour${if (hours > 1) "s" else ""} ago"
        minutes > 0 -> "$minutes minute${if (minutes > 1) "s" else ""} ago"
        seconds > 0 -> "$seconds second${if (seconds > 1) "s" else ""} ago"
        else -> "Just now"
    }
}

@Composable
private fun Author(author: String, modifier: Modifier = Modifier) {
    Text(author, fontSize = 30.sp, fontWeight = FontWeight.Bold, modifier = modifier)
}
