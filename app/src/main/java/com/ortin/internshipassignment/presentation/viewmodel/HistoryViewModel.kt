package com.ortin.internshipassignment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.ortin.internshipassignment.InternshipAssignment
import com.ortin.internshipassignment.data.MainDB

class HistoryViewModel(dataBase: MainDB) : ViewModel() {
    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as InternshipAssignment).database
                return HistoryViewModel(database) as T
            }
        }
    }

    private val _listOfCards = dataBase.cardDao.getAllItems()
    val listOfCards = _listOfCards
}
