package br.com.parreira.kmpcrud

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform