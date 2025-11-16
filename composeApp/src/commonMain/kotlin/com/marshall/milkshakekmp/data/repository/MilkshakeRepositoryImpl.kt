package com.marshall.milkshakekmp.data.repository

import com.marshall.milkshakekmp.domain.model.Milkshake
import com.marshall.milkshakekmp.domain.repository.MilkshakeRepository

class MilkshakeRepositoryImpl : MilkshakeRepository {
    override fun getMilkshakes(): List<Milkshake> {
        return listOf(
            Milkshake(
                name = "Banhoney",
                description = "The best milkshake on the planet",
                imageName = "banhoney",
                colorValue = 0xFFFF8C00,
                price = 12.00
            ),
            Milkshake(
                name = "Strawmilk",
                description = "The best milkshake on the planet",
                imageName = "strawmilk",
                colorValue = 0xFFFF0000,
                price = 11.00
            ),
            Milkshake(
                name = "Chocoffe",
                description = "The best milkshake on the planet",
                imageName = "chocoffe",
                colorValue = 0xFF420E03,
                price = 15.00
            ),
            Milkshake(
                name = "Cosmoberry",
                description = "The best milkshake on the planet",
                imageName = "cosmoberry",
                colorValue = 0xFF6116DA,
                price = 10.00
            )
        )
    }
}
