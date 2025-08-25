package com.eastgrams.lineeditcompdemo.ui.component.linetextedit

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EditLineDialog(
    lineInfo: Pair<Int, String>,
    onDismiss: () -> Unit,
    onSave: (Int, String) -> Unit
) {
    var text by remember { mutableStateOf(lineInfo.second) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("라인 ${lineInfo.first + 1} 수정") },
        text = {
            Column {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("내용") },
                    minLines = 3
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { onSave(lineInfo.first, text) }) {
                Text("저장")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("취소")
            }
        }
    )
}

@Preview
@Composable
private fun EditLineDialogPreview() {
    EditLineDialog(lineInfo = Pair(0, "미리보기 텍스트"), onDismiss = {}, onSave = { _, _ ->})
}