package hamy.yona.td2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_task.view.*

class TasksAdapter(private val tasks: MutableList<Task>) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>()  {
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun getItemCount(): Int = tasks.size

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task) {
            itemView.task_title.text = task.title
            itemView.task_description.text = task.description
            itemView.imageView.setOnClickListener{onDeleteClickListener.invoke(task)}
            itemView.editButton.setOnClickListener {

                onEditClickListener.invoke(task)

            }
        }
    }

    var onEditClickListener : (Task) -> Unit = {}
    var onDeleteClickListener : (Task) -> Unit = {}
}
