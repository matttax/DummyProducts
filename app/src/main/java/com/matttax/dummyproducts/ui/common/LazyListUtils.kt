package com.matttax.dummyproducts.ui.common

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

val LazyPagingItems<*>.isInErrorState: Boolean
    get() {
        return loadState.append is LoadState.Error || loadState.refresh is LoadState.Error
    }
