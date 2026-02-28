package es.imovil.recyclerviewprac

data class Course(
    val nombre: String,
    val profesor: String
){
    companion object {
        fun createCourseList(asignaturas: Array<String>, profesores: Array<String>): List<Course> {
            val courses = mutableListOf<Course>()
            if (asignaturas.size == profesores.size) {
                // zip combina los dos arrays en una lista de pares (nombre, profesor)
                asignaturas.zip(profesores).forEach { (nombre, profesor) ->
                    courses.add(Course(nombre, profesor))
                }
            }
            return courses.toList()
        }
    }
}


