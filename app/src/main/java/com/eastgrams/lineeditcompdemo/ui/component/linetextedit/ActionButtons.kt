package com.eastgrams.lineeditcompdemo.ui.component.linetextedit

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionButtons(
    mode: EditorMode,
    isSelectionMode: Boolean,
    selectedItemCount: Int,
    onEditClick: () -> Unit,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    onDeleteSelectedClick: () -> Unit,
    onClearSelectionClick: () -> Unit,
    modifier : Modifier = Modifier,
    useButtonContainer: Boolean = false,
) {
    Column(
        modifier = modifier.fillMaxWidth().background(Color.Transparent),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Top
    ){
        AnimatedContent(
            targetState = Pair(mode, isSelectionMode),
            transitionSpec = { fadeIn() togetherWith fadeOut() },
        ) { (currentMode, selectionMode) ->
            Row(
                modifier =modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {

                if (currentMode == EditorMode.Edit && selectionMode) {
                    if(useButtonContainer){
                        Text("${selectedItemCount}개 선택됨")
                    }
                    IconButton(onClick = onCancelClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Cancel and go back")
                    }
                    IconButton(onClick = onClearSelectionClick) {
                        Icon(Icons.Filled.Close, contentDescription = "Clear selection")
                    }
                    IconButton(onClick = onDeleteSelectedClick) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete selected items")
                    }
                } else {
                    when (currentMode) {
                        EditorMode.Display -> {
                            IconButton(onClick = onEditClick) {
                                Icon(Icons.Filled.Edit, contentDescription = "Edit")
                            }
                        }
                        EditorMode.Edit -> {
                            IconButton(onClick = onSaveClick) {
                                Icon(Icons.Filled.Check, contentDescription = "Save")
                            }
                        }
                    }
                }
            }
        }
    }
}
