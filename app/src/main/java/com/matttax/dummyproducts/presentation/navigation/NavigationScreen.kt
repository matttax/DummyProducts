package com.matttax.dummyproducts.presentation.navigation

import com.matttax.dummyproducts.presentation.ProductViewModel

sealed class NavigationScreen(val route: String) {
    object SearchProducts : NavigationScreen(SEARCH_SCREEN_ROUTE)
    object ProductData : NavigationScreen("$PRODUCT_SCREEN_ROUTE/{${ProductViewModel.ID_KEY}}") {
        fun navigateById(id: Long) = "$PRODUCT_SCREEN_ROUTE/$id"
    }

    private companion object {
        const val SEARCH_SCREEN_ROUTE = "search"
        const val PRODUCT_SCREEN_ROUTE = "products"
    }
}
