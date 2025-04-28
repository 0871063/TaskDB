package com.example.testdb.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testdb.database.Task
import com.example.testdb.database.TaskDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel (private  val taskDao: TaskDao) : ViewModel() {
    private var _tasks by mutableStateOf<List<Task>>(listOf())
    val tasks : List<Task> get() = _tasks

    init {
        getAllTasks()
    }

    fun getAllTasks(){
        viewModelScope.launch(Dispatchers.IO) {
            _tasks = taskDao.getAllTasks()
        }
    }

    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.insertTask(task)
            val updateTasks = _tasks.toMutableList()
            updateTasks.add(task)
            _tasks = updateTasks
        }
    }

    fun updateTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.updateTask(task)

            _tasks = _tasks.map {
                if(it.id == task.id) task else it
            }
        }
    }

    fun markDone(task: Task){
        val  updateTask = task.copy(isDOne = !task.isDOne)
        viewModelScope.launch(Dispatchers.IO) {
            taskDao.updateTask(task)

            _tasks = _tasks.map {
                if(it.id == task.id) updateTask else it
            }
        }
    }

}