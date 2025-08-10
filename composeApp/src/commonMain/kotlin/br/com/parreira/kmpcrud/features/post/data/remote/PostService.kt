package br.com.parreira.kmpcrud.features.post.data.remote

import br.com.parreira.kmpcrud.core.remote.ApiService
import br.com.parreira.kmpcrud.features.post.entity.PostDto
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PostService(private val apiService: ApiService) {

    private val client = apiService.httpClient
    private val baseUrl = apiService.baseUrl

    suspend fun getAll(): List<PostDto> {
        return client.get("$baseUrl/posts").body()
    }

    suspend fun create(postDto: PostDto): PostDto {
        return client.post("$baseUrl/posts") {
            contentType(ContentType.Application.Json)
            setBody(postDto)
        }.body()
    }

    suspend fun update(id: Int, postDto: PostDto): PostDto {
        return client.put("$baseUrl/posts/$id") {
            contentType(ContentType.Application.Json)
            setBody(postDto)
        }.body()
    }

    suspend fun delete(id: Int) {
        client.delete("$baseUrl/posts/$id")
    }
}