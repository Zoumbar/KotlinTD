package hamy.yona.td2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import hamy.yona.td2.network.Api
import kotlinx.android.synthetic.main.fragment_tasks.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.Response

class TasksFragment : Fragment(R.layout.fragment_tasks) {
    private val coroutineScope = MainScope()
    private val tasks = mutableListOf<Task>()
    private val tasksViewModel by lazy {
        ViewModelProvider(this).get(TasksViewModel::class.java)
    }
    private val adapter = TasksAdapter(tasks)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tasks_recycler_view.adapter = adapter
        tasks_recycler_view.layoutManager = LinearLayoutManager(activity)
        /*adapter.onDeleteClickListener = { task ->
            tasksRepository.sendDeleteTasks(task.id).observe(this, Observer{ result ->
                if (result){
                    tasks.remove(task)
                    adapter.notifyDataSetChanged()
                }  })



        }
*/
        adapter.onEditClickListener = { task ->
            val intent = Intent(activity, TaskActivity::class.java)
            intent.putExtra(TASK_KEY, task)
            startActivityForResult(intent, EDIT_TASK_REQUEST_CODE)


        }

        floatingActionButton.setOnClickListener {

            val intent = Intent(activity, TaskActivity::class.java)
            startActivityForResult(intent, ADD_TASK_REQUEST_CODE)

        }

        tasksViewModel.tasks.observe(this, Observer {
            if (it != null) {
                tasks.clear()
                tasks.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 666 && resultCode == Activity.RESULT_OK) {
            val task = data!!.getSerializableExtra("TASK") as Task
            tasks.add(task)
            adapter.notifyItemInserted(tasks.size)
        }

    }

    companion object {
        private const val ADD_TASK_REQUEST_CODE = 666
        private const val TASK_KEY = "task_key"
        private const val EDIT_TASK_REQUEST_CODE = 1
    }

    override fun onResume() {
        super.onResume()
        tasksViewModel.loadTasks()
        coroutineScope.launch{val userInfo = Api.userService.getInfo().body()
            headertxtview.text=userInfo?.email
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        coroutineScope.cancel()
    }

}