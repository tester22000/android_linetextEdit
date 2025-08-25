package com.eastgrams.lineeditcompdemo.editor


import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import com.eastgrams.lineeditcompdemo.ui.component.linetextedit.EditorMode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorTopBar(
    mode: EditorMode,
    isSelectionMode: Boolean,
    selectedItemCount: Int,
    onEditClick: () -> Unit,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    onDeleteSelectedClick: () -> Unit,
    onClearSelectionClick: () -> Unit
) {
    AnimatedContent(
        targetState = Pair(mode, isSelectionMode),
        transitionSpec = { fadeIn() togetherWith fadeOut() },
        label = "TopBar Animation"
    ) { (currentMode, selectionMode) ->
        if (currentMode == EditorMode.Edit && selectionMode) {
            // 다중 선택 모드 TopBar
            TopAppBar(
                title = { Text("${selectedItemCount}개 선택됨") },
                navigationIcon = {
                    IconButton(onClick = onClearSelectionClick) {
                        Icon(Icons.Filled.Close, contentDescription = "Clear selection")
                    }
                },
                actions = {
                    IconButton(onClick = onDeleteSelectedClick) {
                        Icon(Icons.Filled.Delete, contentDescription = "Delete selected items")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            )
        } else {
            // 기본 또는 편집 모드 TopBar
            TopAppBar(
                title = { Text(if (currentMode == EditorMode.Display) "텍스트 보기" else "줄 단위 편집") },
                navigationIcon = {
                    if (currentMode == EditorMode.Edit) {
                        IconButton(onClick = onCancelClick) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Cancel and go back")
                        }
                    }
                },
                actions = {
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
            )
        }
    }
}