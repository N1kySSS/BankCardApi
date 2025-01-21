package com.ortin.internshipassignment.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NumberNetworkModel(
    val lengthL: Int,
    val luhn: Boolean
)
