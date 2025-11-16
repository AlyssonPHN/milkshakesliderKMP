

package com.marshall.milkshakekmp.di

import com.marshall.milkshakekmp.data.repository.MilkshakeRepositoryImpl
import com.marshall.milkshakekmp.domain.repository.MilkshakeRepository
import com.marshall.milkshakekmp.domain.use_case.GetMilkshakesUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule = module {
    single<MilkshakeRepository> { MilkshakeRepositoryImpl() }
}

val useCaseModule = module {
    factory { GetMilkshakesUseCase(get()) }
}

expect val viewModelModule: Module
