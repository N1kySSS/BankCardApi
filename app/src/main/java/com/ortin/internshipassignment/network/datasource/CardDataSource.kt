package com.ortin.internshipassignment.network.datasource

import android.util.Log
import com.ortin.internshipassignment.network.model.CardNetworkModel
import com.ortin.internshipassignment.network.model.NetworkModel
import io.ktor.client.call.body
import io.ktor.client.request.get

private const val TAG = "RESPONSE_TAG"

class CardDataSource {
    private val client = NetworkModel.client

    suspend fun getCardInfo(cardNumber: String): Result<CardNetworkModel> = runCatching {
        client
            .get("${NetworkModel.BASE_HTTPS_URL}$cardNumber")
            .body<CardNetworkModel>()
    }.onFailure { cause ->
        Log.i(TAG, "Error in get card information: $cause")
    }

}
