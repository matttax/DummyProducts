package com.matttax.dummyproducts.presentation.utils

import androidx.paging.LoadState
import java.net.ConnectException
import java.net.SocketException
import java.net.UnknownHostException

val LoadState.Error?.errorMessageUi: String
    get() = when (this?.error) {
        null -> "Unknown error"
        is SocketException, is ConnectException -> "You're offline. Check your connection"
        is UnknownHostException -> "Failed to connect with host"
        else -> ""
    }
