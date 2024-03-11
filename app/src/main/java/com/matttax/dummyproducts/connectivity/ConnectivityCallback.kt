package com.matttax.dummyproducts.connectivity

import android.net.ConnectivityManager
import android.net.Network

class ConnectivityCallback(
    private val onUnknown: () -> Unit,
    private val onAvailable: () -> Unit,
    private val onLost: () -> Unit
) : ConnectivityManager.NetworkCallback() {
    override fun onAvailable(network: Network) {
        onAvailable.invoke()
    }
    override fun onUnavailable() {
        onUnknown.invoke()
    }
    override fun onLost(network: Network) {
        onLost.invoke()
    }
}
