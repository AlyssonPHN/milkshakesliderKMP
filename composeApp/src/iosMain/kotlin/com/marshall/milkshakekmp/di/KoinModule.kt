

package com.marshall.milkshakekmp.di

import com.marshall.milkshakekmp.ui.milkshake.viewmodel.MilkshakeViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

actual val viewModelModule: Module = module {
    factory { MilkshakeViewModel(get()) }
}
