package com.matttax.dummyproducts.presentation.utils

import kotlinx.coroutines.flow.MutableSharedFlow

typealias RefreshTrigger = MutableSharedFlow<Unit>

fun RefreshTrigger.pull() {
    tryEmit(Unit)
}

fun createRefreshTrigger(): RefreshTrigger {
    return MutableSharedFlow(replay = 1)
}
