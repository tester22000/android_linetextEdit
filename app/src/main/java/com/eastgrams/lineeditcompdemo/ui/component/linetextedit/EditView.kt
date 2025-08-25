package com.eastgrams.lineeditcompdemo.ui.component.linetextedit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EditView(
    lines: List<String>,
    selectedIndices: Set<Int>,
    isSelectionMode: Boolean,
    rangeSelectionAnchor: Int?,
    onTextClick: (Int) -> Unit,
    onCheckboxClick: (Int) -> Unit,
    onCheckboxLongClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    useLineWrap: Boolean = false
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth().fillMaxHeight(),
        //contentPadding = PaddingValues(vertical = 8.dp, horizontal = 8.dp), // 좌우 패딩 추가
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        itemsIndexed(lines, key = { index, _ -> index }) { index, line ->
            EditableLineItem(
                index = index,
                text = line,
                isSelected = selectedIndices.contains(index),
                isSelectionMode = isSelectionMode,
                isRangeAnchor = rangeSelectionAnchor == index, // 👇 anchor 여부 전달
                onTextClick = { onTextClick(index) },
                onCheckboxClick = { onCheckboxClick(index) },
                onCheckboxLongClick = { onCheckboxLongClick(index) },
                useLineWrap=useLineWrap
            )
        }
    }
}