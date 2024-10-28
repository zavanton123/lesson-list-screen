package ru.zavanton.app

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import ru.zavanton.app.ui.theme.ProjectHCITheme

internal data class LessonListUiState(
    val title: String,
    val items: List<LessonListItemUiState>,
)

internal data class LessonListItemUiState(
    val title: String,
    val subtitle: String,
)

@Composable
internal fun LessonListScreen(
    state: LessonListUiState,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Hello ${state.title}!",
        modifier = modifier
    )
}

private class LessonListUiStateProvider : PreviewParameterProvider<LessonListUiState> {

    private val state = LessonListUiState(
        title = "Lessons",
        items = emptyList()
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