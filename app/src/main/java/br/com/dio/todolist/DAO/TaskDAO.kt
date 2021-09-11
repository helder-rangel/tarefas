package br.com.dio.todolist.DAO

import android.content.ContentValues
import android.content.Context
import br.com.dio.todolist.database.DatabaseHelper
import br.com.dio.todolist.model.Task

class TaskDAO (context: Context){
    private lateinit var database: DatabaseHelper

    init {
        this.database = DatabaseHelper(context)
    }

    fun add(task: Task){
        val cv = ContentValues()
        cv.put("title", task.title)
        cv.put("date", task.date)
        cv.put("hour", task.hour)
        this.database.writableDatabase.insert("tasks",null,cv)
    }

    fun get(): MutableList<Task>{
        val list = mutableListOf<Task>()
        val columns = arrayOf("id", "title", "date", "hour")
        val cursor = this.database.readableDatabase.query("tasks", columns, null,null,null,null,null)
        cursor.moveToFirst()
        for(i in 1..cursor.count){
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val title = cursor.getString(cursor.getColumnIndex("title"))
            val date = cursor.getString(cursor.getColumnIndex("date"))
            val hour = cursor.getString(cursor.getColumnIndex("hour"))
            list.add(Task(id, title, date, hour))
            cursor.moveToNext()
        }
        return list
    }

    fun get(id: Int): Task? {
        var list:Task? = null
        val where: String = "id = ?"
        val pWhere = arrayOf(id.toString())
        val columns = arrayOf("id", "title", "date", "hour")
        val cursor =
            this.database.readableDatabase.query("tasks", columns, where, pWhere, null, null, null)
        cursor.moveToFirst()
        for (i in 1..cursor.count) {
            if (cursor.count == 1) {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val title = cursor.getString(cursor.getColumnIndex("title"))
                val date = cursor.getString(cursor.getColumnIndex("date"))
                val hour = cursor.getString(cursor.getColumnIndex("hour"))
                list = (Task(id, title, date, hour))
                cursor.moveToNext()
                return list
            }
        }
        return null
    }

    fun delete(task: Task){
        val where = "id = ?"
        val pWhere = arrayOf(task.toString())
        val columns = arrayOf("id", "title", "date", "hour")
        this.database.writableDatabase.delete("tasks", where,pWhere)
    }

    fun update(task: Task){
        val cv = ContentValues()
        val where = "id = ?"
        val pWhere = arrayOf(task.toString())

        cv.put("title", task.title)
        cv.put("date", task.date)
        cv.put("hour", task.hour)
        this.database.writableDatabase.update("tasks", cv, where, pWhere)
    }
}