package com.matttax.dummyproducts.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.matttax.dummyproducts.Product
import com.matttax.dummyproducts.data.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _productsState: MutableStateFlow<PagingData<Product>> = MutableStateFlow(value = PagingData.empty())
    val productsState: MutableStateFlow<PagingData<Product>> get() = _productsState

    init {
        productRepository.getProducts()
            .flowOn(Dispatchers.IO)
            .cachedIn(viewModelScope)
            .onEach {
                _productsState.value = it
            }.launchIn(viewModelScope)
    }

}
