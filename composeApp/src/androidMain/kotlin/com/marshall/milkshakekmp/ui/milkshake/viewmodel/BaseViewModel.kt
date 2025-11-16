package com.marshall.milkshakekmp.ui.milkshake.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope as androidXViewModelScope
import kotlinx.coroutines.CoroutineScope

actual open class BaseViewModel : ViewModel() {
    actual val viewModelScope: CoroutineScope = androidXViewModelScope
}
