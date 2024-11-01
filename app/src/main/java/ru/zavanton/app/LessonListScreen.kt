package ru.zavanton.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    query = "Search...",
                    onQueryChange = { },
                    onSearch = { },
                    active = false,
                    onActiveChange = { },
                    content = {}
                )

                var isShown by rememberSaveable {
                    mutableStateOf(false)
                }

                Row(
                    modifier = Modifier.padding(16.dp),
                ) {
                    Text(
                        text = "Easy",
                        modifier = Modifier.clickable { isShown = !isShown }
                    )
                    Spacer(modifier = Modifier.weight(1F))

                    Text(
                        text = "Complete",
                        modifier = Modifier.clickable { isShown = !isShown },
                    )
                    Spacer(modifier = Modifier.weight(1F))

                    Text(
                        text = "Premium",
                        modifier = Modifier.clickable { isShown = !isShown },
                    )
                }

                if (isShown) {
                    Column(
                        modifier = Modifier.width(IntrinsicSize.Max)
                    ) {
                        SelectionOption("Beginner", true)
                        SelectionOption("Intermediate")
                        SelectionOption("Advanced")
                    }
                }
            }
        },
        content = { padding ->
            var isEmpty by rememberSaveable { mutableStateOf(true) }
            if (isEmpty) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { isEmpty = false },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "No lessons found",
                    )
                    Image(
                        painter = painterResource(id = R.drawable.baseline_format_list_bulleted_24),
                        contentDescription = "ok",
                    )
                }

            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(padding)
                        .padding(horizontal = 16.dp)
                ) {
                    items(state.items) { item ->
                        LessonItem(item)
                    }
                }
            }
        }
    )
}

@Composable
private fun SelectionOption(
    label: String,
    isSelected: Boolean = false,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = label)
        Spacer(modifier = Modifier.weight(1F))
        RadioButton(selected = isSelected, onClick = {})
    }
}

@Composable
private fun LessonItem(
    state: LessonListItemUiState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .padding(end = 16.dp)
                .size(60.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        ) {
            AsyncImage(
                modifier = modifier
                    .size(60.dp),
                model = state.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }

        Column {
            Text(text = state.title)
            Text(text = state.subtitle)
        }

        Spacer(modifier = Modifier.weight(1F))

        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xffE4E0E1))
                .padding(horizontal = 8.dp)
                .padding(vertical = 4.dp),
            text = state.duration,
        )
    }
}

internal class LessonListUiStateProvider : PreviewParameterProvider<LessonListUiState> {
    val item = LessonListItemUiState(
        imageUrl = "https://picsum.photos/200/200",
        title = "Lesson 1 - London",
        subtitle = "London is the capital of Gr... ",
        duration = "10:00",
        difficulty = "EASY",
        streak = "3 days streak"
    )

    val items = List(size = 20) {
        item
    }

    val state = LessonListUiState(
        title = "Lessons",
        items = items,
    )

    val emptyState = state.copy(items = emptyList())

    override val values: Sequence<LessonListUiState> = sequenceOf(state, emptyState)
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