package com.ortin.internshipassignment.data.entity

data class CountryNetworkInfo(
    val numeric: String?,
    val alpha2: String?,
    val name: String?,
    val emoji: String?,
    val currency: String?,
    val latitude: Int?,
    val longitude: Int?
) {
    override fun toString(): String {
        return "numeric=$numeric, alpha2=$alpha2, name=$name, emoji=$emoji, currency=$currency, latitude=$latitude, longitude=$longitude"
    }
}
