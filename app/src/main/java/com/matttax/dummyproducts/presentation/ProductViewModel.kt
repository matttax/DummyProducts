package com.matttax.dummyproducts.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matttax.dummyproducts.connectivity.ConnectionState
import com.matttax.dummyproducts.connectivity.NetworkConnectivityProvider
import com.matttax.dummyproducts.data.model.ProductLoadingException
import com.matttax.dummyproducts.domain.ProductsRepository
import com.matttax.dummyproducts.presentation.model.CartCountEvent
import com.matttax.dummyproducts.presentation.model.ProductState
import com.matttax.dummyproducts.presentation.utils.RefreshTrigger
import com.matttax.dummyproducts.presentation.utils.pull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val networkConnectivityProvider: NetworkConnectivityProvider,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _productState = MutableStateFlow<ProductState>(ProductState.Loading)
    val productState = _productState.asStateFlow()

    // количество добавляемых в корзину товаров
    private val _toCartAddedCount = MutableStateFlow(0)
    val toCartAddedCount = _toCartAddedCount.asStateFlow()

    private val productId: Long = checkNotNull(savedStateHandle[ID_KEY])
    private val refreshTrigger = RefreshTrigger()

    init {
        observeProduct()
        observeNetwork()
    }

    fun refresh() {
        refreshTrigger.pull()
    }

    fun changeSelectedCount(event: CartCountEvent) {
        val productStable = _productState.value
        if (productStable is ProductState.Result) {
            _toCartAddedCount.update {
                when(event) {
                    CartCountEvent.INCREMENT -> it + 1
                    CartCountEvent.DECREMENT -> it - 1
                    CartCountEvent.CLEAR -> 0
                }.coerceIn(0, productStable.product.inStock)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeProduct() {
        refreshTrigger
            .onEach {
                _productState.value = ProductState.Loading
            }
            .flatMapLatest {
                productsRepository.getProductById(productId)
            }
            .onEach {
                if (it.isSuccess) {
                    _productState.value = it.getOrNull()
                        ?.let { product -> ProductState.Result(product) }
                        ?: ProductState.NotFound
                } else {
                    _productState.value = when (val ex = it.exceptionOrNull()) {
                        is ProductLoadingException.NotFoundException -> ProductState.NotFound
                        else -> ProductState.NetworkError(
                            message = ex?.message ?: ProductState.NETWORK_ERROR_DEFAULT_MESSAGE
                        )
                    }
                }
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
        refreshTrigger.pull()
    }

    private fun observeNetwork() {
        networkConnectivityProvider.networkStatus
            .filter { it == ConnectionState.AVAILABLE }
            .onEach {
                if (_productState.value.isError) {
                    refreshTrigger.pull()
                }
            }.launchIn(viewModelScope)
    }

    companion object {
        const val ID_KEY = "id"
    }
}
