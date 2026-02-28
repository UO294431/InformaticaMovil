package es.imovil.recyclerviewprac

import androidx.recyclerview.widget.DiffUtil

data class Course(var asignatura: String, var profesor: String) {

    object DIFF_CALLBACK : DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        fun createCourseList(asignaturas: Array<String>, profesores: Array<String>): List<Course> {
            val courses = mutableListOf<Course>()
            if (asignaturas.size == profesores.size) {
                asignaturas.zip(profesores).forEach { (asignatura, profesor) ->
                    courses.add(Course(asignatura, profesor))
                }
            }
            return courses.toList()
        }
    }
}
