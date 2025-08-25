package com.eastgrams.lineeditcompdemo.editor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eastgrams.lineeditcompdemo.ui.component.linetextedit.DisplayView
import com.eastgrams.lineeditcompdemo.ui.component.linetextedit.EditLineDialog
import com.eastgrams.lineeditcompdemo.ui.component.linetextedit.EditView
import com.eastgrams.lineeditcompdemo.ui.component.linetextedit.EditorMode
import com.eastgrams.lineeditcompdemo.ui.component.linetextedit.LineTextEdit
import com.eastgrams.lineeditcompdemo.ui.component.linetextedit.LineTextEditViewModel

@Composable
fun LineTextEditorScreen( ) {
    var content1 by remember { mutableStateOf("Sample Text\nline1 Some Long Text Some Long Text Some Long Text Some Long Text Some Long Text \nline2\nline3") }
    var content2 by remember { mutableStateOf("Sample Text\nline1 Some Long Text Some Long Text Some Long Text Some Long Text Some Long Text \nline2\nline3") }
    Scaffold(
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            Text("Demo")
            LineTextEdit(
                content1,
                modifier = Modifier.fillMaxWidth()
                    .weight(1f),
                onSave = { text ->
                    content1 = text
                },
                useLineWrap = false,
                useButtonContainer = false,
            )
            LineTextEdit(
                content2,
                modifier = Modifier.fillMaxWidth()
                    .weight(1f),
                onSave = { text ->
                    content2 = text
                },
                useLineWrap = true,
                useButtonContainer = true,
            )
        }
    }
}