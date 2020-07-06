package com.example.search_user_test.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.search_user_test.model.UserItem

@Database(entities = [UserItem::class], version = 1, exportSchema = false)
abstract class AbsRoomDatabase : androidx.room.RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        private var instance: AbsRoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AbsRoomDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AbsRoomDatabase::class.java, "weknot_database"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}