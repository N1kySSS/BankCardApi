package com.ortin.internshipassignment.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CountryNetworkModel(
    val numeric: String? = null,
    val alpha2: String? = null,
    val name: String? = null,
    val emoji: String? = null,
    val currency: String? = null,
    val latitude: Int? = null,
    val longitude: Int? = null
)
