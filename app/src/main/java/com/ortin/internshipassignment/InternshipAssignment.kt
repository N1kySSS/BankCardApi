package com.ortin.internshipassignment

import android.app.Application
import com.ortin.internshipassignment.data.MainDB
import com.ortin.internshipassignment.network.model.NetworkModel.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class InternshipAssignment : Application() {
    val database by lazy { MainDB.createDataBase(this@InternshipAssignment) }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@InternshipAssignment)

            modules(networkModule)
        }
    }
}
