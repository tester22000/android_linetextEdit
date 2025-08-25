package com.eastgrams.lineeditcompdemo.ui.component.linetextedit

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LineTextEdit(
    initialValue: String,
    modifier: Modifier = Modifier,
    onSave: (String) -> Unit,
    useLineWrap: Boolean = false,
    useButtonContainer: Boolean = false,
    viewModel: LineTextEditViewModel = hiltViewModel(key = "warp=${useLineWrap}container=${useButtonContainer}"),
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(initialValue) {
        viewModel.initialize(initialValue)
    }
    LineTextEditContainer(
        modifier = modifier.fillMaxWidth().fillMaxHeight(),
        useButtonContainer = useButtonContainer
    ) {
        if ( useButtonContainer ){
            ActionButtons(
                mode = uiState.mode,
                isSelectionMode = uiState.isSelectionMode,
                selectedItemCount = uiState.selectedIndices.size,
                onEditClick = viewModel::enterEditMode,
                onSaveClick = {
                    viewModel.save()
                    onSave(uiState.lines.joinToString("\n"))
                },
                onCancelClick = {
                    viewModel.cancel()
                },
                onDeleteSelectedClick = viewModel::deleteSelectedLines,
                onClearSelectionClick = viewModel::clearSelection,
                useButtonContainer = true
            )
            LineTextEditContent(
                viewModel = viewModel,
                uiState = uiState,
                useLineWrap= useLineWrap,
            )
        } else {
            LineTextEditContent(
                viewModel = viewModel,
                uiState = uiState
            )
            ActionButtons(
                mode = uiState.mode,
                isSelectionMode = uiState.isSelectionMode,
                selectedItemCount = uiState.selectedIndices.size,
                onEditClick = viewModel::enterEditMode,
                onSaveClick = {
                    viewModel.save()
                    onSave(uiState.lines.joinToString("\n"))
                },
                onCancelClick = {
                    viewModel.cancel()
                },
                onDeleteSelectedClick = viewModel::deleteSelectedLines,
                onClearSelectionClick = viewModel::clearSelection,
                useButtonContainer = false
            )
        }
    }
}
