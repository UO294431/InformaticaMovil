package es.imovil.recyclerviewprac

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddCourseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)

        val etCourseName = findViewById<EditText>(R.id.etCourseName)
        val etProfessorName = findViewById<EditText>(R.id.etProfessorName)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val courseName = etCourseName.text.toString()
            val professorName = etProfessorName.text.toString()

            if (courseName.isNotEmpty() && professorName.isNotEmpty()) {
                val resultIntent = Intent()
                resultIntent.putExtra("COURSE_NAME", courseName)
                resultIntent.putExtra("PROFESSOR_NAME", professorName)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}