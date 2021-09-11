package br.com.dio.todolist.model

import java.net.UnknownHostException
import java.util.*

class Task {
    var title: String
    var date: String = "00/00/0000"
    var hour: String = "0"
    var id: Int = 0

    constructor(title: String) {
        this.id = -1
        this.title = title
        this.hour = hour
        this.date = date
    }

    constructor(id: Int, title: String, date:String, hour: String) {
        this.id = id
        this.title = title
        this.date = date
        this.hour = hour
    }

    override fun toString(): String {
        return "${id} - ${title} - ${date} - ${hour}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Task

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}
