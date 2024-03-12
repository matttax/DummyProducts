package com.matttax.dummyproducts.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.matttax.dummyproducts.domain.model.ProductDomainModel
import com.matttax.dummyproducts.connectivity.ConnectionState
import com.matttax.dummyproducts.connectivity.NetworkConnectivityProvider
import com.matttax.dummyproducts.domain.ProductsRepository
import com.matttax.dummyproducts.presentation.model.CategoryUiModel
import com.matttax.dummyproducts.presentation.model.ProductQuery
import com.matttax.dummyproducts.presentation.model.SearchSingleEvent
import com.matttax.dummyproducts.presentation.utils.RefreshTrigger
import com.matttax.dummyproducts.presentation.utils.pull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import java.util.*
import java.util.concurrent.atomic.AtomicReference
import javax.inject.Inject
import kotlin.collections.HashSet

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    networkConnectivityProvider: NetworkConnectivityProvider,
) : ViewModel() {

    private val _productsState: MutableStateFlow<PagingData<ProductDomainModel>> = MutableStateFlow(value = PagingData.empty())
    val productsState = _productsState.asStateFlow()

    private val _queryText = MutableStateFlow("")
    val queryText = _queryText.asStateFlow()

    private val _categoriesList = MutableStateFlow<List<CategoryUiModel>?>(null)
    val categoriesList = _categoriesList.asStateFlow()

    private val searchSingleEventChanel = Channel<SearchSingleEvent>()
    val eventFlow = searchSingleEventChanel.receiveAsFlow()

    val networkConnectionState = networkConnectivityProvider.networkStatus.stateIn(
        initialValue = ConnectionState.UNAVAILABLE,
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(FLOW_STOP_TIMEOUT_MS)
    )

    private val refreshTrigger = RefreshTrigger()
    private var listUpdatedFlag = AtomicReference(false)
    private val changedCategories = Collections.synchronizedSet(HashSet<String>())

    init {
        observeData()
        observeCategories()
    }

    fun onSearchTextChanged(newText: String) {
        _queryText.value = newText
    }

    fun onSearch() {
        refreshTrigger.pull()
    }

    fun notifyCategorySelectionStateChanged(name: String) {
        if (changedCategories.contains(name)) {
            changedCategories.remove(name)
        } else {
            changedCategories.add(name)
        }
    }

    fun submitFilterChanges() {
        _categoriesList.update {
            it?.toMutableList()?.apply {
                forEachIndexed { index, model ->
                    if (changedCategories.contains(model.name)) {
                        set(index, CategoryUiModel(model.name, !model.isSelected))
                    }
                }
            }
        }
        discardFilterChanges()
        refreshTrigger.pull()
    }

    fun discardFilterChanges() {
        changedCategories.clear()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeData() {
        refreshTrigger
            .onEach { listUpdatedFlag.set(true) }
            .map {
                ProductQuery(
                    text = _queryText.value,
                    categories = _categoriesList.value
                        ?.filter { it.isSelected }
                        ?.map { it.name }
                )
            }
            .flatMapLatest { query ->
                if (query == ProductQuery.GET_ALL_QUERY) {
                    productsRepository.getProducts()
                } else if (query.categories == null) {
                    productsRepository.getProducts(query.text)
                } else {
                    productsRepository.getProducts(query.text)
                        .map { it.filter { product ->
                            query.categories.contains(product.category)
                        }
                    }
                }.catch { ex ->
                    searchSingleEventChanel.send(
                        SearchSingleEvent.ProductsLoadingError(ex.message.toString())
                    )
                }
            }
            .flowOn(Dispatchers.IO)
            .cachedIn(viewModelScope)
            .onEach {
                _productsState.value = it
                if (listUpdatedFlag.get()) {
                    searchSingleEventChanel.send(SearchSingleEvent.ListUpdated)
                    listUpdatedFlag.set(false)
                }
            }.launchIn(viewModelScope)
        refreshTrigger.pull()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeCategories() {
        networkConnectionState
            .filter { it == ConnectionState.AVAILABLE && _categoriesList.value == null }
            .flatMapLatest {
                productsRepository.getCategories()
            }
            .flowOn(Dispatchers.IO)
            .onEach {
                when {
                    it.isSuccess -> {
                        _categoriesList.value = it.getOrNull()?.map { category ->
                            CategoryUiModel(category.name, true)
                        } ?: emptyList()
                    }
                    else -> searchSingleEventChanel.send(SearchSingleEvent.CategoriesLoadingError)
                }
            }.launchIn(viewModelScope)
    }

    companion object {
        const val FLOW_STOP_TIMEOUT_MS = 5000L
    }
}
