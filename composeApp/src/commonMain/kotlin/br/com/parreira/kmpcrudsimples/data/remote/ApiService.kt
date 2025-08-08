package br.com.parreira.kmpcrudsimples.data.remote

import br.com.parreira.kmpcrudsimples.data.dto.PostDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ApiService {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    private val baseUrl = "https://jsonplaceholder.typicode.com"

    suspend fun getPosts(): List<PostDto> {
        return client.get("$baseUrl/posts").body()
    }

    suspend fun createPost(postDto: PostDto): PostDto {
        return client.post("$baseUrl/posts") {
            contentType(ContentType.Application.Json)
            setBody(postDto)
        }.body()
    }

    suspend fun updatePost(id: Int, postDto: PostDto): PostDto {
        return client.put("$baseUrl/posts/$id") {
            contentType(ContentType.Application.Json)
            setBody(postDto)
        }.body()
    }

    suspend fun deletePost(id: Int) {
        client.delete("$baseUrl/posts/$id")
    }

}