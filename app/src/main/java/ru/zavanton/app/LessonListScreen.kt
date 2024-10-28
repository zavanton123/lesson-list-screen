package ru.zavanton.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import ru.zavanton.app.ui.theme.ProjectHCITheme

internal data class LessonListUiState(
    val title: String,
    val items: List<LessonListItemUiState>,
)

internal data class LessonListItemUiState(
    val imageUrl: String,
    val title: String,
    val subtitle: String,
    val duration: String,
    val difficulty: String,
    val streak: String,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LessonListScreen(
    state: LessonListUiState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            SearchBar(
                query = "Search...",
                onQueryChange = { },
                onSearch = { },
                active = false,
                onActiveChange = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                content = {
                }
            )
        },
        content = { padding ->
            LazyColumn(
                modifier = Modifier.padding(padding)
            ) {
                items(state.items) { item ->
                    LessonItem(item)
                }
            }
        }
    )
}

@Composable
private fun LessonItem(
    state: LessonListItemUiState,
    modifier: Modifier = Modifier,
) {
    Row {
        AsyncImage(
            modifier = modifier
                .size(60.dp),
            model = state.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Column {
            Text(
                text = state.title,
            )

            Text(
                text = state.subtitle,
            )

        }

    }
}

internal class LessonListUiStateProvider : PreviewParameterProvider<LessonListUiState> {

    val item = LessonListItemUiState(
        imageUrl = "https://picsum.photos/200/200",
        title = "Lesson 1 - About London",
        subtitle = "London is the capital of Great Britain",
        duration = "10:00",
        difficulty = "EASY",
        streak = "3 days streak"
    )

    val items = listOf(item, item, item, item, item)

    val state = LessonListUiState(
        title = "Lessons",
        items = items,
    )

    override val values: Sequence<LessonListUiState> = sequenceOf(
        state
    )
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true,
)
private fun GreetingPreview(
    @PreviewParameter(LessonListUiStateProvider::class)
    state: LessonListUiState,
) {
    ProjectHCITheme {
        LessonListScreen(
            state = state,
        )
    }
}