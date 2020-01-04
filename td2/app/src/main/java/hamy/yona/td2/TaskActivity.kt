package hamy.yona.td2

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_task.*

class TaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        buttonValidation.setOnClickListener {
            val task = Task(id = "id_666", title = editTitle.text.toString(), description = editDescription.text.toString())
            intent.putExtra("TASK", task)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }


    }
}
