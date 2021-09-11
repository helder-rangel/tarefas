package br.com.dio.todolist.datasource

import android.content.ContentValues
import android.content.Context
import br.com.dio.todolist.database.DatabaseHelper
import br.com.dio.todolist.model.Task

class TaskDataSource (context: Context) {
    private val list = arrayListOf<Task>()
    private lateinit var database: DatabaseHelper


    init {
        this.database = DatabaseHelper(context)
    }


    //fun getList() = list.toList()
    fun getList(): MutableList<Task>{
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

    fun insertTask(task: Task){
        val cv = ContentValues()
        cv.put("title", task.title)
        cv.put("date", task.date)
        cv.put("hour", task.hour)
        this.database.writableDatabase.insert("tasks",null,cv)
    }

    fun findById(taskId: Int) = list.find { it.id == taskId }

    fun deleteTask(task: Task) {
        list.remove(task)
    }
}