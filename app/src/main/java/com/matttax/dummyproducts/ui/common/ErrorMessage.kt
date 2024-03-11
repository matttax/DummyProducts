package com.matttax.dummyproducts.ui.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.matttax.dummyproducts.presentation.utils.ui.StringUtils

@Composable
fun ErrorMessage(
    message: String,
    modifier: Modifier = Modifier,
    spaceBetween: Dp = 25.dp,
    isTextLarge: Boolean = true,
    onClickRetry: () -> Unit
) {
    Column(
        modifier = modifier.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            style = if (isTextLarge) MaterialTheme.typography.bodyLarge else MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center,
            maxLines = 2
        )
        Spacer(modifier = Modifier.height(spaceBetween))
        OutlinedButton(
            onClick = onClickRetry
        ) {
            Text(
                text = StringUtils.Titles.RETRY_BUTTON_TEXT,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}
