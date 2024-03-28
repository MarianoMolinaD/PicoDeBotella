package com.portafoliowebmariano.picobotella.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.portafoliowebmariano.picobotella.model.Challenge
import com.portafoliowebmariano.picobotella.utils.constants.NAME_DB

@Database(entities = [Challenge::class] , version = 1, exportSchema = false)
abstract class DBChallenge : RoomDatabase() {
    abstract fun daoChallenge(): DaoChallenge
    companion object{
        @Volatile
        private var INSTANCE : DBChallenge? = null

        fun getDataBase(context : Context): DBChallenge{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DBChallenge::class.java,
                    NAME_DB
                )
                    .createFromAsset("database/${NAME_DB}")
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}