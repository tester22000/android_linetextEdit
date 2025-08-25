package com.eastgrams.lineeditcompdemo.ui.component.linetextedit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun LineTextEditContainer(
    modifier: Modifier = Modifier,
    useButtonContainer: Boolean = false,
    content: @Composable () -> Unit,
){
    if(useButtonContainer){
        Column(modifier=modifier){
            content()
        }
    } else {
        Box(modifier=modifier){
            content()
        }
    }
}