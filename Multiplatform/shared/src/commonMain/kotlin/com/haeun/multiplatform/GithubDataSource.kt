package com.haeun.multiplatform

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class GithubDataSource {
    private val httpClient = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            })
        }
    }

    suspend fun getGithubUsers(q: String): GithubUsersModel = withContext(Dispatchers.IO) {
        return@withContext httpClient.get("https://api.github.com/search/users") {
            url {
                parameters.append("q", "user")
            }
        }.body<GithubUsersModel>()
    }
}