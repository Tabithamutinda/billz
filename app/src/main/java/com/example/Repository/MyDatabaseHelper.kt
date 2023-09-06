package com.example.Repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "billz.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create your table here
        val createTableQuery = """
            CREATE TABLE IF NOT EXISTS bills_table (
                bill_id INTEGER PRIMARY KEY AUTOINCREMENT,
                bill_name TEXT,
                amount INTEGER,
                frequency TEXT,
                due_date TEXT,
                user_id TEXT
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Handle database schema upgrades here
        // You can drop and recreate tables, or perform other necessary migrations
    }
}
