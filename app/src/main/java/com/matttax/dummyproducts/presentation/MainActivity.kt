package com.matttax.dummyproducts.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.matttax.dummyproducts.presentation.components.Filters
import com.matttax.dummyproducts.presentation.components.SearchBar
import com.matttax.dummyproducts.presentation.components.ProductsPagingList
import com.matttax.dummyproducts.presentation.model.ProductSingleEvent
import com.matttax.dummyproducts.presentation.utils.StringUtils
import com.matttax.dummyproducts.ui.common.ScrollToTopButton
import com.matttax.dummyproducts.ui.theme.DummyProductsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DummyProductsTheme {
                val productViewModel by viewModels<ProductViewModel>()
                ProductsListScreen(productViewModel)
            }
        }
    }
}

@Composable
fun ProductsListScreen(viewModel: ProductViewModel) {
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
        viewModel.errorFlow.collectLatest {
            when (it) {
                is ProductSingleEvent.ProductsLoadingError -> {
                    snackbarHostState.showSnackbar(it.errorMessage)
                }
                is ProductSingleEvent.CategoriesLoadingError -> {
                    if (filtersExpanded) {
                        snackbarHostState.showSnackbar(StringUtils.Errors.CATEGORIES_LOADING_ERROR_MESSAGE)
                    }
                }
                is ProductSingleEvent.ListUpdated -> {
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
                listState = listState
            )
        }
    }
    BackHandler(isFocused || filtersExpanded) {
        viewModel.discardFilterChanges()
        filtersExpanded = false
        focusManager.clearFocus()
    }
}
