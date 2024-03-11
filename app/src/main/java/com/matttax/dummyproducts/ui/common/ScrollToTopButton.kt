package com.matttax.dummyproducts.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.matttax.dummyproducts.presentation.utils.ui.AnimationUtils
import kotlinx.coroutines.launch

@Composable
fun ScrollToTopButton(
    listState: LazyListState
) {
    val scope = rememberCoroutineScope()
    val showScrollToTopButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex >= 5
        }
    }
    AnimatedVisibility(
        visible = showScrollToTopButton,
        enter = AnimationUtils.scaleIn,
        exit = AnimationUtils.scaleOut
    ) {
        FloatingActionButton(
            shape = CircleShape,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onTertiary,
            onClick = {
                scope.launch {
                    listState.scrollToItem(0)
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = null
            )
        }
    }
}
