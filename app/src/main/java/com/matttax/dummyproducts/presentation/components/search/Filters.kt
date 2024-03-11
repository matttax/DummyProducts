package com.matttax.dummyproducts.presentation.components.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.matttax.dummyproducts.presentation.model.CategoryUiModel
import com.matttax.dummyproducts.presentation.utils.ui.AnimationUtils
import com.matttax.dummyproducts.presentation.utils.ui.StringUtils
import com.matttax.dummyproducts.ui.common.ExpansionArrow
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Filters(
    filtersExpanded: Boolean = false,
    categoriesListFlow: Flow<List<CategoryUiModel>?>,
    onStateChanged: (name: String) -> Unit,
    onClick: () -> Unit,
    onApply: () -> Unit
) {
    val categories by categoriesListFlow.collectAsState(initial = null)
    var barYPosition by remember { mutableStateOf(0) }
    Column {
        Row(
            modifier = Modifier
                .clickable { onClick() }
                .fillMaxWidth()
                .onPlaced {
                    barYPosition = it.size.height
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            ExpansionArrow(filtersExpanded)
            Spacer(Modifier.width(2.dp))
            Text(
                text = StringUtils.Titles.FILTERS_EXPAND_BUTTON_TEXT,
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Popup(offset = IntOffset(0, barYPosition)) {
            AnimatedVisibility(
                visible = filtersExpanded,
                enter = AnimationUtils.popUpEnter(AnimationUtils.PopUpDirection.DOWN),
                exit = AnimationUtils.popUpExit(AnimationUtils.PopUpDirection.DOWN),
            ) {
                FlowRow(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(5.dp)
                        .fillMaxWidth()
                ) {
                    categories?.forEach { model ->
                        CategoryCard(model) {
                            onStateChanged(model.name)
                        }
                    }.also {
                        it?.let {
                            OutlinedButton(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    onApply()
                                }
                            ) {
                                Text(
                                    text = StringUtils.Titles.FILTERS_APPLY_BUTTON_TEXT,
                                    style = MaterialTheme.typography.labelLarge
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
