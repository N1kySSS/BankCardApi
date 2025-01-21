package com.ortin.internshipassignment.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CardNetworkModel(
    val number: NumberNetworkModel,
    val scheme: String,
    val type: String,
    val brand: String,
    val prepaid: String,
    val country: CountryNetworkModel,
    val bank: BankNetworkModel
)
