package br.com.parreira.kmpcrudsimples.data.remote

import br.com.parreira.kmpcrudsimples.data.dto.PostDto
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

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