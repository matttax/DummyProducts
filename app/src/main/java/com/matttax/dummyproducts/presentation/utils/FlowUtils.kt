package com.matttax.dummyproducts.presentation.utils

import kotlinx.coroutines.flow.MutableSharedFlow

fun MutableSharedFlow<Unit>.pull() {
    tryEmit(Unit)
}
