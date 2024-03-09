package com.matttax.dummyproducts.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.matttax.dummyproducts.presentation.components.ProductsPagingList
import com.matttax.dummyproducts.ui.theme.DummyProductsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DummyProductsTheme {
                val productViewModel by viewModels<ProductViewModel>()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProductsPagingList(pagingListFlow = productViewModel.productsState)
                }
            }
        }
    }
}
