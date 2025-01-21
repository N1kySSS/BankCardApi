package com.ortin.internshipassignment.network.model

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

object NetworkModel {
    const val BASE_HTTPS_URL = "https://lookup.binlist.net/"

    val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    val networkModule = module {
        single<HttpClient> {
            client
        }
    }
}
