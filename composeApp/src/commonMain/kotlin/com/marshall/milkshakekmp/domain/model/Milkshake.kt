package com.marshall.milkshakekmp.domain.model

data class Milkshake(
    val name: String,
    val description: String,
    val imageName: String,
    val colorValue: Long,
    val price: Double
)
