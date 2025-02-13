package com.ortin.internshipassignment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.ortin.internshipassignment.InternshipAssignment
import com.ortin.internshipassignment.data.MainDB
import com.ortin.internshipassignment.data.entity.BankNetworkInfo
import com.ortin.internshipassignment.data.entity.CardEntity
import com.ortin.internshipassignment.data.entity.CountryNetworkInfo
import com.ortin.internshipassignment.data.entity.NumberNetworkInfo
import com.ortin.internshipassignment.network.datasource.CardDataSource
import com.ortin.internshipassignment.network.model.CardNetworkModel
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CardViewModel(dataBase: MainDB) : ViewModel() {
    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as InternshipAssignment).database
                return CardViewModel(database) as T
            }
        }
    }

    private val db = dataBase

    private val _searchText = MutableStateFlow("45717360")
    val searchText = _searchText.asStateFlow()

    private val _cardInfo = MutableStateFlow<CardNetworkModel?>(null)
    val cardInfo = _cardInfo.asStateFlow()

    init {
        viewModelScope.launch {
            updateCardInformation()
        }
    }

    private suspend fun getCardInfo(cardNumber: String) =
        withContext(Dispatchers.IO + CoroutineName("GetCardInfoCoroutine")) {
            CardDataSource().getCardInfo(cardNumber).getOrNull()
        }

    fun onSearchTextChange(text: String) {
        _searchText.value = text.trim().replace(" ", "")
    }

    fun updateCardInformation() {
        viewModelScope.launch {
            _cardInfo.value = getCardInfo(searchText.value)
            saveCardInfo(searchText.value, _cardInfo.value)
        }
    }

    //TODO(check logic of work)
    private suspend fun saveCardInfo(cardNumber: String?, cardNetworkModel: CardNetworkModel?) {
        db.cardDao.insertCard(
            CardEntity(
                cardNumber = cardNumber?.toIntOrNull() ?: 0,
                number = NumberNetworkInfo(
                    length = cardNetworkModel?.number?.length,
                    luhn = cardNetworkModel?.number?.luhn
                ),
                scheme = cardNetworkModel?.scheme,
                type = cardNetworkModel?.type,
                brand = cardNetworkModel?.brand,
                prepaid = cardNetworkModel?.prepaid,
                country = CountryNetworkInfo(
                    numeric = cardNetworkModel?.country?.numeric,
                    alpha2 = cardNetworkModel?.country?.alpha2,
                    name = cardNetworkModel?.country?.name,
                    emoji = cardNetworkModel?.country?.emoji,
                    currency = cardNetworkModel?.country?.currency,
                    latitude = cardNetworkModel?.country?.latitude,
                    longitude = cardNetworkModel?.country?.longitude
                ),
                bank = BankNetworkInfo(
                    bankName = cardNetworkModel?.bank?.name,
                    url = cardNetworkModel?.bank?.url,
                    phone = cardNetworkModel?.bank?.phone,
                    city = cardNetworkModel?.bank?.city
                )
            )
        )
    }
}
