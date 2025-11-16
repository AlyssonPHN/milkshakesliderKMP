package com.marshall.milkshakekmp.domain.use_case

import com.marshall.milkshakekmp.domain.model.Milkshake
import com.marshall.milkshakekmp.domain.repository.MilkshakeRepository

class GetMilkshakesUseCase(private val repository: MilkshakeRepository) {
    operator fun invoke(): List<Milkshake> {
        return repository.getMilkshakes()
    }
}
