package br.com.dio.todolist.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, "tasks.dat", null, 1) {

    override fun onCreate(db: SQLiteDatabase?){
        val sql = "create table tasks( " +
                "id integer primary key autoincrement, " +
                "title text, " +
                "date text, " +
                "hour text)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table tasks")
        this.onCreate(db)
    }
}