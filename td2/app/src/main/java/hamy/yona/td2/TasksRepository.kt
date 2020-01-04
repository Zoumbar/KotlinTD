package hamy.yona.td2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import hamy.yona.td2.network.Api
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class TasksRepository {
    private val tasksService = Api.tasksService


    suspend fun sendDeleteTasks(id: String): Boolean {
        val tasksResponse = tasksService.getTasks()
        return tasksResponse.isSuccessful
    }

    suspend fun loadTasks(): List<Task>? {
        val tasksResponse = tasksService.getTasks()
        Log.e("loadTasks", tasksResponse.toString())
        return if (tasksResponse.isSuccessful) tasksResponse.body() else null
    }
}