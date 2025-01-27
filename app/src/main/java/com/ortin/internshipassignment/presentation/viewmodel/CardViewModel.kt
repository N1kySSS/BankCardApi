package com.ortin.internshipassignment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ortin.internshipassignment.network.datasource.CardDataSource
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking

class CardViewModel : ViewModel() {
    private val _searchText = MutableStateFlow("45717360")
    val searchText = _searchText.asStateFlow()

    private var _cardInfo = awaitingResult(searchText.value)
    var cardInfo = _cardInfo

    private fun getCardInfo(cardNumber: String) =
        viewModelScope.async(Dispatchers.IO + CoroutineName("GetCardInfoCoroutine")) {
            CardDataSource().getCardInfo(cardNumber)
        }

    private fun awaitingResult(cardNumber: String) = runBlocking {
        getCardInfo(cardNumber).await().getOrNull()
    }

    fun onSearchTextChange(text: String) {
        _searchText.value = text.trim().replace(" ", "")
    }

    fun updateCardInformation() {
        _cardInfo = awaitingResult(searchText.value)
        cardInfo = _cardInfo
    }
}
