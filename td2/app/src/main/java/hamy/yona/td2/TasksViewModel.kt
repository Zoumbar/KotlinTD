package hamy.yona.td2

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import hamy.yona.td2.network.Api
import kotlinx.coroutines.launch


class TasksViewModel: ViewModel() {
    val tasks = MutableLiveData<List<Task>?>()
    private val repository = TasksRepository()

    fun loadTasks() {
        viewModelScope.launch {
            val result = repository.loadTasks()
            tasks.postValue(result)
        }
    }
}