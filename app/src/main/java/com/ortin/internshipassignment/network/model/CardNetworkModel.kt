package com.ortin.internshipassignment.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CardNetworkModel(
    val number: NumberNetworkModel? = null,
    val scheme: String? = null,
    val type: String? = null,
    val brand: String? = null,
    val prepaid: String? = null,
    val country: CountryNetworkModel? = null,
    val bank: BankNetworkModel? = null
)
