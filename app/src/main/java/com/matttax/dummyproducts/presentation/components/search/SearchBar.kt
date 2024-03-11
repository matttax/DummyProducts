package com.matttax.dummyproducts.presentation.components.search

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.matttax.dummyproducts.R
import com.matttax.dummyproducts.presentation.utils.ui.StringUtils
import kotlinx.coroutines.flow.Flow

@Composable
fun SearchBar(
    searchText: Flow<String>,
    onChange: (String) -> Unit,
    onSearch: () -> Unit,
    interactionSource: MutableInteractionSource
) {
    val query by searchText.collectAsState("")
    TextField(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_baseline_search_24),
                contentDescription = null
            )
        },
        value = query,
        onValueChange = onChange,
        placeholder = {
            Text(
                text = StringUtils.Titles.SEARCH_BAR_PLACEHOLDER_TEXT,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = { onSearch() }
        ),
        shape = MaterialTheme.shapes.large,
        colors = TextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent
        ),
        textStyle = MaterialTheme.typography.titleMedium,
        interactionSource = interactionSource
    )
}
