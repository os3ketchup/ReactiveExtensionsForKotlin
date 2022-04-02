package com.example.reactiveextensionsforkotlin.models.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.reactiveextensionsforkotlin.models.Consumer

@Database(entities =  [Consumer::class], version = 1)
abstract class AppDataBase:RoomDatabase() {
    abstract fun myDao():MyDao

    companion object{
        private var instance:AppDataBase?=null

        @Synchronized
        fun getInstance(context: Context):AppDataBase{
            if (instance==null){
                instance= Room.databaseBuilder(context,AppDataBase::class.java,"my_db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}