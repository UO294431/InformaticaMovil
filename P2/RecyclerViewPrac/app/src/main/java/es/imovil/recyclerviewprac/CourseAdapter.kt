package es.imovil.recyclerviewprac

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.imovil.recyclerviewprac.databinding.ItemLayoutBinding

class CourseAdapter : ListAdapter<Course, CourseAdapter.ViewHolder>(Course.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = getItem(position)
        holder.bind(course)
    }

    class ViewHolder(private val itemBinding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(course: Course) {
            itemBinding.courseText.text = course.asignatura
            itemBinding.teacherText.text = course.profesor
        }
    }
}