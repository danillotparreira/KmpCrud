package br.com.parreira.kmpcrud.features.post.entity.mapper

import br.com.parreira.kmpcrud.features.post.entity.PostDto
import br.com.parreira.kmpcrud.features.post.entity.Post

fun PostDto.toDomain(): Post {
    return Post(
        id = this.id ?: -1,
        title = this.title,
        body = this.body
    )
}