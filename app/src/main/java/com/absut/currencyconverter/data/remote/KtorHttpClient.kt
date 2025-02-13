package com.absut.currencyconverter.data.remote

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val TIME_OUT = 10_000

val ktorHttpClient = HttpClient(Android) {

	install(ContentNegotiation){
		json(Json{
			prettyPrint = true
			isLenient = true
			ignoreUnknownKeys = true
		})
	}

	engine {
		connectTimeout = TIME_OUT
		socketTimeout = TIME_OUT
	}

	defaultRequest {
		url(ApiRoutes.BASE_URL)
		header(HttpHeaders.ContentType, ContentType.Application.Json)
	}

	Logging {
		logger = object : Logger {
			override fun log(message: String) {
				Log.v("Logger Ktor =>", message)
			}
		}
		level = LogLevel.ALL
	}

	ResponseObserver { response ->
		Log.d("HTTP status:", "${response.status.value}")
	}

}