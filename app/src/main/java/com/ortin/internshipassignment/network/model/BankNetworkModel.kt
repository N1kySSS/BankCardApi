package com.ortin.internshipassignment.network.model

import kotlinx.serialization.Serializable

@Serializable
data class BankNetworkModel(
    val name: String,
    val url: String,
    val phone: String,
    val city: String
)
