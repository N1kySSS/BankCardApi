package com.ortin.internshipassignment.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NumberNetworkModel(
    val length: Int? = null,
    val luhn: Boolean? = null
)
