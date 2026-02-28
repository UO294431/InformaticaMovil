package es.imovil.recyclerviewprac

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import es.imovil.recyclerviewprac.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adaptador: CourseAdapter

    private val addCourseLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val courseName = data?.getStringExtra("COURSE_NAME")
            val professorName = data?.getStringExtra("PROFESSOR_NAME")

            if (courseName != null && professorName != null) {
                val newCourse = Course(courseName, professorName)
                // Obtenemos la lista actual, creamos una nueva lista con el nuevo elemento y la enviamos
                val currentList = adaptador.currentList.toMutableList()
                currentList.add(newCourse)
                adaptador.submitList(currentList)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val cursos = Course.createCourseList(
            resources.getStringArray(R.array.asignaturas),
            resources.getStringArray(R.array.profesores)
        )
        
        adaptador = CourseAdapter()
        
        binding.contenido.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.contenido.recyclerView.setHasFixedSize(true)
        binding.contenido.recyclerView.adapter = adaptador
        
        // Asignarle al adaptador la lista a mostrar con submitList()
        adaptador.submitList(cursos)

        binding.fab.setOnClickListener {
            // launchAddCourseActivity()
            adaptador.submitList(cursos.slice((1..5).step(2)))
        }
    }

    private fun launchAddCourseActivity() {
        val intent = Intent(this, AddCourseActivity::class.java)
        addCourseLauncher.launch(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_course -> {
                launchAddCourseActivity()
                true
            }
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}