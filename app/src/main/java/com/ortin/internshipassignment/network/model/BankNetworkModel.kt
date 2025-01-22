package com.ortin.internshipassignment.network.model

import kotlinx.serialization.Serializable

@Serializable
data class BankNetworkModel(
    val name: String? = null,
    val url: String? = null,
    val phone: String? = null,
    val city: String? = null
)
