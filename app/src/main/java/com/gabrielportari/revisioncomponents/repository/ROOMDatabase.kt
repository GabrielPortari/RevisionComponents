package com.gabrielportari.revisioncomponents.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gabrielportari.revisioncomponents.model.UserROOM

@Database(entities = [UserROOM::class], version = 1)
abstract class ROOMDatabase : RoomDatabase() {
    abstract fun userDAO(): UserDAO
}