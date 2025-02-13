package com.ortin.internshipassignment.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ortin.internshipassignment.data.entity.CardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(cardEntity: CardEntity)

    @Query("SELECT * FROM cards_table")
    fun getAllItems(): Flow<List<CardEntity>>
}
