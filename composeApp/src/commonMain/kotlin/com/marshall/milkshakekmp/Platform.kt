package com.marshall.milkshakekmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform