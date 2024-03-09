package com.matttax.dummyproducts.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.matttax.dummyproducts.Product
import com.matttax.dummyproducts.presentation.model.toUiModel
import com.matttax.dummyproducts.presentation.utils.errorMessageUi
import com.matttax.dummyproducts.ui.common.ErrorMessage
import com.matttax.dummyproducts.ui.common.ProgressBar
import kotlinx.coroutines.flow.Flow

@Composable
fun ProductsPagingList(pagingListFlow: Flow<PagingData<Product>>) {
    val pagingProducts = pagingListFlow.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            count = pagingProducts.itemCount,
            key = { pagingProducts[it]?.id ?: 0 }
        ) { index ->
            pagingProducts[index]?.toUiModel()?.let { ProductItem(it) }
        }
        pagingProducts.apply {
            when {
                loadState.append is LoadState.Loading -> {
                    item {
                        ProgressBar(
                            modifier = Modifier
                                .padding(15.dp)
                                .fillMaxWidth(),
                            size = 40.dp
                        )
                    }
                }
                loadState.refresh is LoadState.Loading -> {
                    item {
                        ProgressBar(
                            modifier = Modifier.fillParentMaxSize(),
                            size = 100.dp
                        )
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    val error = pagingProducts.loadState.refresh as? LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier.fillParentMaxSize(),
                            message = error.errorMessageUi,
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val error = pagingProducts.loadState.append as? LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier.fillMaxWidth(),
                            message = error.errorMessageUi,
                            spaceBetween = 10.dp,
                            isTextLarge = false,
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }
    }
}
