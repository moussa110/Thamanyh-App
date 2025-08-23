package com.mousa.thamnyahapp.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mousa.thamnyahapp.domain.model.Content
import com.mousa.thamnyahapp.domain.model.HomeSection

@Composable
fun QueueSection(section: HomeSection) {
    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        section.content.forEach { item ->
            QueueCard(item = item)
        }
    }
}

@Composable
fun QueueCard(item: Content, onClick: () -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            CustomAsyncImage(
                imageUrl = item.avatarUrl,
                contentDescription = item.name,
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.Top)
                    .clip(MaterialTheme.shapes.medium)
            )

            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(item.name, style = MaterialTheme.typography.bodyMedium)
                Text(
                    item.description,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }
    }
}