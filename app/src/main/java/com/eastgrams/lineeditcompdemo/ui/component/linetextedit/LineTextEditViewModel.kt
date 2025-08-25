package com.eastgrams.lineeditcompdemo.ui.component.linetextedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.max
import kotlin.math.min

enum class EditorMode { Display, Edit }

data class LineTextUiState(
    val mode: EditorMode = EditorMode.Display,
    val originalContent: String = "",
    val currentContent: String = "",
    val lines: List<String> = emptyList(),
    val isSelectionMode: Boolean = false,
    val selectedIndices: Set<Int> = emptySet(),
    val editingLine: Pair<Int, String>? = null,
    val rangeSelectionAnchor: Int? = null
)

@HiltViewModel
class LineTextEditViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(LineTextUiState())
    val uiState = _uiState.asStateFlow()

    fun initialize(content: String) {
        _uiState.update {
            it.copy(
                originalContent = content,
                currentContent = content,
                lines = content.split('\n')
            )
        }
    }

    fun updateContent(newContent: String) {
        _uiState.update { it.copy(currentContent = newContent) }
    }

    fun enterEditMode() {
        _uiState.update {
            it.copy(
                mode = EditorMode.Edit,
                lines = it.currentContent.split('\n')
            )
        }
    }

    private fun enterDisplayMode(resetContent: Boolean = false) {
        _uiState.update {
            it.copy(
                mode = EditorMode.Display,
                isSelectionMode = false,
                selectedIndices = emptySet(),
                currentContent = if (resetContent) it.originalContent else it.lines.joinToString("\n")
            )
        }
    }

    fun save() {
        enterDisplayMode(resetContent = false)
    }

    fun cancel() {
        enterDisplayMode(resetContent = true)
    }

    fun onCheckboxLongClick(index: Int) {
        _uiState.update {
            it.copy(
                isSelectionMode = true,
                // 길게 누른 아이템을 범위 선택의 시작점으로 설정합니다.
                rangeSelectionAnchor = index,
                // 시작점도 선택된 상태에 포함시킵니다.
                selectedIndices = it.selectedIndices + index
            )
        }
    }

    fun onCheckboxClick(index: Int) {
        _uiState.update { state ->
            if (state.rangeSelectionAnchor != null) {
                if (state.rangeSelectionAnchor == index) {
                    state.copy(rangeSelectionAnchor = null)
                }
                else {
                    val start = min(state.rangeSelectionAnchor, index)
                    val end = max(state.rangeSelectionAnchor, index)
                    val range = (start..end).toSet()
                    state.copy(
                        selectedIndices = state.selectedIndices + range,
                        rangeSelectionAnchor = null
                    )
                }
            }
            else {
                val newSelection = if (state.selectedIndices.contains(index)) {
                    state.selectedIndices - index
                } else {
                    state.selectedIndices + index
                }
                state.copy(
                    selectedIndices = newSelection,
                    isSelectionMode = newSelection.isNotEmpty()
                )
            }
        }
    }

    fun onTextClick(index: Int) {
        if (!_uiState.value.isSelectionMode) {
            showEditDialog(index)
        }
    }

    fun clearSelection() {
        _uiState.update {
            it.copy(
                isSelectionMode = false,
                selectedIndices = emptySet(),
                rangeSelectionAnchor = null
            )
        }
    }

    fun deleteSelectedLines() {
        viewModelScope.launch {
            _uiState.update { state ->
                val newLines = state.lines.filterIndexed { index, _ -> !state.selectedIndices.contains(index) }
                state.copy(
                    lines = newLines,
                    selectedIndices = emptySet(),
                    isSelectionMode = false
                )
            }
        }
    }

    fun showEditDialog(index: Int) {
        _uiState.update {
            it.copy(editingLine = Pair(index, it.lines[index]))
        }
    }

    fun dismissEditDialog() {
        _uiState.update { it.copy(editingLine = null) }
    }

    fun updateLine(index: Int, newText: String) {
        _uiState.update { state ->
            val currentLines = state.lines.toMutableList()
            if (newText.isBlank()) {
                currentLines.removeAt(index)
            } else {
                val newTextLines = newText.split('\n')
                currentLines[index] = newTextLines.first()
                if (newTextLines.size > 1) {
                    currentLines.addAll(index + 1, newTextLines.drop(1))
                }
            }
            state.copy(lines = currentLines, editingLine = null)
        }
    }
}