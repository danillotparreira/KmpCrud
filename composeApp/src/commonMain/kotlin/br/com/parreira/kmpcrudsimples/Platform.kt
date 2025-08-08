package br.com.parreira.kmpcrudsimples

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform