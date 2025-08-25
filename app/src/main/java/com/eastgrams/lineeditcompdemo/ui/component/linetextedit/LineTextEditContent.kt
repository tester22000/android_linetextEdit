package com.eastgrams.lineeditcompdemo.ui.component.linetextedit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LineTextEditContent(
    viewModel: LineTextEditViewModel,
    uiState : LineTextUiState,
    modifier: Modifier = Modifier,
    useLineWrap: Boolean = false,
){
    Column (
        modifier = modifier.fillMaxSize(),
    ) {
        when (uiState.mode) {
            EditorMode.Display -> DisplayView(
                content = uiState.currentContent,
                onContentChange = viewModel::updateContent,
            )
            EditorMode.Edit -> EditView(
                lines = uiState.lines,
                selectedIndices = uiState.selectedIndices,
                isSelectionMode = uiState.isSelectionMode,
                rangeSelectionAnchor = uiState.rangeSelectionAnchor,
                onTextClick = viewModel::onTextClick,
                onCheckboxClick = viewModel::onCheckboxClick,
                onCheckboxLongClick = viewModel::onCheckboxLongClick,
                useLineWrap = useLineWrap,
            )
        }
        uiState.editingLine?.let { lineInfo ->
            EditLineDialog(
                lineInfo = lineInfo,
                onDismiss = viewModel::dismissEditDialog,
                onSave = viewModel::updateLine
            )
        }
    }
}