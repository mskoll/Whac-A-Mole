package com.mskoll.smart.whac_a_mole.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Score::class], version = 1, exportSchema = false)
abstract class WAMDatabase : RoomDatabase() {

    abstract fun scoreDao(): ScoreDao

    companion object {
        @Volatile
        private var INSTANCE: WAMDatabase? = null

        fun getDatabase(context: Context): WAMDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WAMDatabase::class.java,
                    "wam_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}