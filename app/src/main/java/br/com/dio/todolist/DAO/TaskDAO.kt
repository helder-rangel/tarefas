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

    fun get(id: Int): Task?{
        val where = "id = ?"
        val pWhere = arrayOf(id.toString())
        val columns = arrayOf("id", "title", "date", "hour")
        val cursor = this.database.readableDatabase.query("tasks",columns, id,where,pwhere)
        if(cursor.count == 1){
            return id
        }
        return null
    }

    fun delete(task: Task){
        val where = "id = ?"
        val pWhere = arrayOf(task.id.toString())
        val columns = arrayOf("id", "title", "date", "hour")
        this.database.writableDatabase.delete("tasks", where,pWhere)
    }

    fun update(task: Task){
        val cv = ContentValues()
        val where = "id = ?"
        val pWhere = arrayOf(task.id.toString())

        cv.put("title")
        cv.put("date")
        cv.put("hour")
        this.database.writableDatabase.update("tasks", cv, where, pwhere)
    }
}