package com.matttax.dummyproducts.presentation.screens

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.matttax.dummyproducts.presentation.ProductViewModel
import com.matttax.dummyproducts.presentation.components.product.LoadedProductLandscape
import com.matttax.dummyproducts.presentation.components.product.LoadedProductPortrait
import com.matttax.dummyproducts.presentation.model.CartCountEvent
import com.matttax.dummyproducts.presentation.model.ProductState
import com.matttax.dummyproducts.ui.common.ErrorMessage
import com.matttax.dummyproducts.ui.common.ProgressBar

@Composable
fun ProductItemScreen(viewModel: ProductViewModel) {
    val productState by viewModel.productState.collectAsStateWithLifecycle()
    val toCartAdded by viewModel.toCartAddedCount.collectAsStateWithLifecycle()
    val configuration = LocalConfiguration.current
    when (val state = productState) {
        is ProductState.Result -> {
            if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                LoadedProductPortrait(
                    product = state.product,
                    currentCount = viewModel.toCartAddedCount,
                    onChangeCountInCart = viewModel::changeSelectedCount
                )
            } else {
                LoadedProductLandscape(state.product, viewModel.toCartAddedCount, viewModel::changeSelectedCount)
            }
        }
        is ProductState.Loading -> {
            ProgressBar(
                modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primaryContainer),
                size = 100.dp
            )
        }
        is ProductState.NetworkError -> {
            ErrorMessage(
                modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primaryContainer),
                message = state.message
            ) {
                viewModel.refresh()
            }
        }
        is ProductState.NotFound -> {
            ErrorMessage(
                modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primaryContainer),
                message = "Product not found"
            ) {
                viewModel.refresh()
            }
        }
    }
    BackHandler(productState is ProductState.Result && toCartAdded > 0) {
        viewModel.changeSelectedCount(CartCountEvent.CLEAR)
    }
}
