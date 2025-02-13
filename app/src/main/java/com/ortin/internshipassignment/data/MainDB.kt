package com.ortin.internshipassignment.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ortin.internshipassignment.data.dao.CardDao
import com.ortin.internshipassignment.data.entity.CardEntity

@Database(
    entities = [CardEntity::class],
    version = 1
)
abstract class MainDB : RoomDatabase() {
    abstract val cardDao: CardDao

    companion object {
        fun createDataBase(context: Context): MainDB {
            return Room.databaseBuilder(
                context,
                MainDB::class.java,
                "cards.db"
            ).build()
        }
    }
}
