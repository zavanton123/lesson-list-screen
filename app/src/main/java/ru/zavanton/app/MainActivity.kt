package ru.zavanton.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import ru.zavanton.app.ui.theme.ProjectHCITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectHCITheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LessonListScreen(
                        state = LessonListUiStateProvider().state,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
