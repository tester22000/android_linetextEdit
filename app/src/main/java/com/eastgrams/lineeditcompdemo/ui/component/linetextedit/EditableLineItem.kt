package com.eastgrams.lineeditcompdemo.ui.component.linetextedit

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EditableLineItem(
    index: Int,
    text: String,
    isSelected: Boolean,
    isSelectionMode: Boolean,
    isRangeAnchor: Boolean,
    onTextClick: () -> Unit,
    onCheckboxClick: () -> Unit,
    onCheckboxLongClick: () -> Unit,
    useLineWrap: Boolean = false
) {
    val backgroundColor by animateColorAsState(
        targetValue = when {
            isRangeAnchor -> MaterialTheme.colorScheme.tertiaryContainer // 범위 선택 시작점
            isSelected -> MaterialTheme.colorScheme.secondaryContainer    // 일반 선택
            else -> MaterialTheme.colorScheme.surface                      // 선택 안됨
        },
        animationSpec = tween(durationMillis = 300),
        label = "BackgroundColorAnimation"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(backgroundColor)
            .combinedClickable(
                onClick = onTextClick,
                onLongClick = onCheckboxClick,
            )
            .padding(vertical = 12.dp)
            .padding(start = 16.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .combinedClickable(
                    onClick = onCheckboxClick,
                    onLongClick = onCheckboxLongClick
                )
        ) {
            Checkbox(
                checked = isSelected,
                onCheckedChange = null
            )
        }
        Spacer(Modifier.width(8.dp))
        Text(
            text = "${index + 1}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = if (useLineWrap)Int.MAX_VALUE else 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
    }
}