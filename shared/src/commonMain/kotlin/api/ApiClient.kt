package api

import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.serialization.Serializable

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

object ApiClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    private val httpClient = HttpClient()

    suspend fun getPosts(): String {
        return httpClient.get("$BASE_URL/posts").bodyAsText()
    }
}

@Serializable
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)