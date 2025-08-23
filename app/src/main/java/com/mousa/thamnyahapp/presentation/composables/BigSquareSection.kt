package com.mousa.thamnyahapp.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mousa.thamnyahapp.domain.model.AudioArticleContent
import com.mousa.thamnyahapp.domain.model.HomeSection

@Composable
fun BigSquareSection(section: HomeSection) {
        LazyRow(modifier = Modifier.padding(start = 8.dp)) {
            items(
                count = section.content.size) { index ->
                val item = section.content[index]
                SquareContentCard(item = item, Modifier.size(180.dp))
            }
        }
}


@Preview
@Composable
fun BigSquareContentCardPreview() {
    MaterialTheme {
        SquareContentCard(
            item = AudioArticleContent(
                id = "asas",
                name = "Sample Content",
                avatarUrl = "",
                authorName = "Author Name",
                description = "description",
                releaseDate = "",
                duration = 100,
                score = 2.5f
            ), Modifier.width(180.dp)
        )
    }
}
