package com.matttax.dummyproducts.presentation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.matttax.dummyproducts.presentation.SearchViewModel
import com.matttax.dummyproducts.presentation.components.search.Filters
import com.matttax.dummyproducts.presentation.components.search.ProductsPagingList
import com.matttax.dummyproducts.presentation.components.search.SearchBar
import com.matttax.dummyproducts.presentation.model.SearchSingleEvent
import com.matttax.dummyproducts.presentation.utils.ui.StringUtils
import com.matttax.dummyproducts.ui.common.ScrollToTopButton
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductsListScreen(
    viewModel: SearchViewModel,
    onItemClick: (Long) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    var filtersExpanded by rememberSaveable {
        mutableStateOf(false)
    }
    val listState = rememberLazyListState()
    if (isFocused) {
        filtersExpanded = false
    }
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(true) {
        viewModel.eventFlow.collectLatest {
            when (it) {
                is SearchSingleEvent.ProductsLoadingError -> {
                    snackbarHostState.showSnackbar(it.errorMessage)
                }
                is SearchSingleEvent.CategoriesLoadingError -> {
                    if (filtersExpanded) {
                        snackbarHostState.showSnackbar(StringUtils.Errors.CATEGORIES_LOADING_ERROR_MESSAGE)
                    }
                }
                is SearchSingleEvent.ListUpdated -> {
                    if (listState.firstVisibleItemIndex >= 1) {
                        listState.scrollToItem(0)
                    }
                }
            }
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = { ScrollToTopButton(listState) }
    ) {
        Column {
            SearchBar(
                searchText = viewModel.queryText,
                onChange = viewModel::onSearchTextChanged,
                onSearch = {
                    viewModel.onSearch()
                    focusManager.clearFocus()
                },
                interactionSource = interactionSource
            )
            Filters(
                filtersExpanded = filtersExpanded,
                categoriesListFlow = viewModel.categoriesList,
                onStateChanged = viewModel::notifyCategorySelectionStateChanged,
                onApply = {
                    viewModel.submitFilterChanges()
                    filtersExpanded = false
                },
                onClick = {
                    filtersExpanded = !filtersExpanded
                    if (filtersExpanded) {
                        focusManager.clearFocus()
                    } else {
                        viewModel.discardFilterChanges()
                    }
                }
            )
            ProductsPagingList(
                pagingListFlow = viewModel.productsState,
                networkConnectionState = viewModel.networkConnectionState,
                listState = listState,
                onItemClick = {
                    filtersExpanded = false
                    onItemClick(it)
                }
            )
        }
    }
    BackHandler(isFocused || filtersExpanded) {
        viewModel.discardFilterChanges()
        filtersExpanded = false
        focusManager.clearFocus()
    }
}
