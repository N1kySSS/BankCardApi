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

    private fun getCardInfo() =
        viewModelScope.async(Dispatchers.IO + CoroutineName("GetCardInfoCoroutine")) {
            CardDataSource().getCardInfo("") // TODO( add userInput )
        }

    private fun awaitingResult() = runBlocking {
        getCardInfo().await().getOrNull()
    }
}
