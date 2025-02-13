package com.ortin.internshipassignment.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards_table")
data class CardEntity(
    @PrimaryKey
    val cardNumber: Int,

    @Embedded
    val number: NumberNetworkInfo?,

    val scheme: String?,

    val type: String?,

    val brand: String?,

    val prepaid: String?,

    @Embedded
    val country: CountryNetworkInfo?,

    @Embedded
    val bank: BankNetworkInfo?
)
