package com.matttax.dummyproducts.presentation.utils

import kotlinx.coroutines.flow.MutableSharedFlow

object RefreshTrigger {
    operator fun invoke(): MutableSharedFlow<Unit> {
        return MutableSharedFlow(replay = 1)
    }
}

fun MutableSharedFlow<Unit>.pull() {
    tryEmit(Unit)
}
