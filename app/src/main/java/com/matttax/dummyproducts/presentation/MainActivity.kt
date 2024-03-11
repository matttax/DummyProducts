package com.matttax.dummyproducts.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.matttax.dummyproducts.presentation.navigation.NavigationScreen
import com.matttax.dummyproducts.presentation.screens.ProductItemScreen
import com.matttax.dummyproducts.presentation.screens.ProductsListScreen
import com.matttax.dummyproducts.presentation.utils.ui.AnimationUtils
import com.matttax.dummyproducts.ui.theme.DummyProductsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DummyProductsTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = NavigationScreen.SearchProducts.route
                ) {
                    composable(
                        route = NavigationScreen.SearchProducts.route
                    ) {
                        val searchViewModel = hiltViewModel<SearchViewModel>()
                        ProductsListScreen(searchViewModel) { id ->
                            navController.navigate(NavigationScreen.ProductData.navigateById(id)) {
                                popUpTo(NavigationScreen.SearchProducts.route) // чтобы в бэкстке не было несольких экранов с товарами
                            }
                        }
                    }
                    composable(
                        route = NavigationScreen.ProductData.route,
                        enterTransition = { AnimationUtils.navigationSlideInAnimation },
                        exitTransition = { AnimationUtils.navigationSlideOutAnimation },
                        arguments = listOf(
                            navArgument(ProductViewModel.ID_KEY) { type = NavType.LongType }
                        ),
                    ) {
                        val productViewModel = hiltViewModel<ProductViewModel>()
                        ProductItemScreen(productViewModel)
                    }
                }
            }
        }
    }
}
