package com.ortin.internshipassignment.data.entity

data class NumberNetworkInfo(
    val length: Int?,
    val luhn: Boolean?
) {
    override fun toString(): String {
        return "length=$length, luhn=$luhn"
    }
}
