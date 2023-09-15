package com.example.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.Model.Bill

@Database(entities = arrayOf(Bill::class), version = 1)
abstract class BillsDb : RoomDatabase(){

    abstract fun BillsDao() : BillsDao

    companion object {
        private var database : BillsDb ? = null
        fun getDatabase(context:Context): BillsDb{
            if (database==null){
                database = Room.databaseBuilder(context, BillsDb::class.java, "billsDb").fallbackToDestructiveMigration().build()
            }
            return database as BillsDb
        }
    }
}