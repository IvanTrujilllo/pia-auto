package com.example.barbershop

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper : SQLiteOpenHelper {
    companion object {
        const val DATA_BASE_NAME = "barbershop";
        const val DATA_BASE_VERSION = 1;
    }

    private val tableName: String;

    constructor(context: Context, tableName: String) : super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION) {
        this.tableName = tableName;
    }

    public override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            String.format(
                "CREATE TABLE IF NOT EXISTS appointments(id INTEGER PRIMARY KEY AUTOINCREMENT, name text, lastname text, age int, date text, service text)"
            )
        );
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }
}