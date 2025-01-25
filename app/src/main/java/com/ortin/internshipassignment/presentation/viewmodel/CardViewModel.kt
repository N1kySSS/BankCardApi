package com.ortin.internshipassignment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ortin.internshipassignment.network.datasource.CardDataSource
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class CardViewModel : ViewModel() {
    private val _cardInfo = awaitingResult()
    val cardInfo = _cardInfo

    private fun getCardInfo() =
        viewModelScope.async(Dispatchers.IO + CoroutineName("GetCardInfoCoroutine")) {
            CardDataSource().getCardInfo("22002404") // TODO( add userInput )
        }

    private fun awaitingResult() = runBlocking {
        getCardInfo().await().getOrNull()
    }
}
