package br.com.parreira.kmpcrudsimples.data.mapper

import br.com.parreira.kmpcrudsimples.data.dto.PostDto
import br.com.parreira.kmpcrudsimples.domain.model.Post

fun PostDto.toDomain(): Post {
    return Post(
        id = this.id ?: -1,
        title = this.title,
        body = this.body
    )
}