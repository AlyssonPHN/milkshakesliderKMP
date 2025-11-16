package com.marshall.milkshakekmp.ui.milkshake.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

actual open class BaseViewModel {
    actual val viewModelScope: CoroutineScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
}
