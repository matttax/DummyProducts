package com.matttax.dummyproducts.presentation.components.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.matttax.dummyproducts.domain.ProductDomainModel
import com.matttax.dummyproducts.connectivity.ConnectionState
import com.matttax.dummyproducts.presentation.model.toUiModel
import com.matttax.dummyproducts.presentation.utils.ui.StringUtils
import com.matttax.dummyproducts.ui.common.ErrorMessage
import com.matttax.dummyproducts.ui.common.ProgressBar
import com.matttax.dummyproducts.ui.common.isInErrorState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductsPagingList(
    pagingListFlow: Flow<PagingData<ProductDomainModel>>,
    networkConnectionState: Flow<ConnectionState>,
    listState: LazyListState,
    onItemClick: (Long) -> Unit
) {
    val pagingProducts = pagingListFlow.collectAsLazyPagingItems()
    LaunchedEffect(true) {
        networkConnectionState.collectLatest {
            if (it == ConnectionState.AVAILABLE && pagingProducts.isInErrorState) {
                pagingProducts.retry()
            }
        }
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = listState
    ) {
        items(
            count = pagingProducts.itemCount,
            key = { pagingProducts[it]?.id ?: 0 }
        ) { index ->
            pagingProducts[index]?.toUiModel()?.let {
                ProductItem(it, onItemClick)
            }
        }
        pagingProducts.run {
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
                            message = StringUtils.Errors.getPagingErrorMessage(error),
                            onClickRetry = { retry() }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val error = pagingProducts.loadState.append as? LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier.fillMaxWidth(),
                            message = StringUtils.Errors.getPagingErrorMessage(error),
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
