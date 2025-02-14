package com.ortin.internshipassignment.data.entity

data class BankNetworkInfo(
    val bankName: String?,
    val url: String?,
    val phone: String?,
    val city: String?
) {
    override fun toString(): String {
        return "bankName=$bankName, url=$url, phone=$phone, city=$city"
    }
}
