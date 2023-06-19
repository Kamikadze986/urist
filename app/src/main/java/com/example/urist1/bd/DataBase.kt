package com.example.urist1.bd

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.urist1.bd.users.UsersDao
import com.example.urist1.bd.users.UsersData

@Database(
    entities = [UsersData::class],
    version = 1,
    exportSchema = false
)
abstract class DataBase : RoomDatabase() {

    abstract fun playlistDao(): UsersDao




    companion object {
        private const val DATABASE_NAME = "URIST_DB"
        private var playlistDatabase: DataBase? = null

        fun getInstance(context: Context): DataBase {
            if (playlistDatabase == null) {
                playlistDatabase = Room.databaseBuilder(
                    context,
                    DataBase::class.java, DATABASE_NAME
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return playlistDatabase!!
        }
    }
}