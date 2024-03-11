package com.matttax.dummyproducts.presentation.utils.ui

import androidx.paging.LoadState
import java.net.ConnectException
import java.net.SocketException
import java.net.UnknownHostException

object StringUtils {

     object Errors {
         fun getPagingErrorMessage(state: LoadState.Error?): String {
             return when (state?.error) {
                 null -> "Unknown error"
                 is SocketException, is ConnectException -> "You're offline. Check your connection"
                 is UnknownHostException -> "Failed to connect with host"
                 else -> "Loading error occurred"
             }
         }

         const val CATEGORIES_LOADING_ERROR_MESSAGE = "Unable to browse categories. Check your connection"
     }

    object Titles {
        const val SEARCH_BAR_PLACEHOLDER_TEXT = "Search"
        const val FILTERS_EXPAND_BUTTON_TEXT = "Filters"
        const val FILTERS_APPLY_BUTTON_TEXT = "Apply"
        const val RETRY_BUTTON_TEXT = "Retry"
    }

}
