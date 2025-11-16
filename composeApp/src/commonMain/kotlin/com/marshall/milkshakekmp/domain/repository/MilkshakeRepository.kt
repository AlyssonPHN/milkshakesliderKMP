package com.marshall.milkshakekmp.domain.repository

import com.marshall.milkshakekmp.domain.model.Milkshake

interface MilkshakeRepository {
    fun getMilkshakes(): List<Milkshake>
}
