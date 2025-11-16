package com.marshall.milkshakekmp.ui.milkshake.viewmodel

import kotlinx.coroutines.CoroutineScope

expect open class BaseViewModel() {
    val viewModelScope: CoroutineScope
}
