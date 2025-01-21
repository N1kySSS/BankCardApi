package com.ortin.internshipassignment.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CountryNetworkModel(
    val numeric: String,
    val alpha2: String,
    val name: String,
    val emoji: String,
    val currency: String,
    val latitude: Int,
    val longitude: Int
)
