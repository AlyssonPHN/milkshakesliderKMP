
package com.marshall.milkshakekmp.di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            repositoryModule,
            useCaseModule,
            viewModelModule
        )
    }
}
