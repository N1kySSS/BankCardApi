package com.ortin.internshipassignment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ortin.internshipassignment.network.datasource.CardDataSource
import com.ortin.internshipassignment.network.model.CardNetworkModel
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CardViewModel : ViewModel() {
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
        }
    }
}
