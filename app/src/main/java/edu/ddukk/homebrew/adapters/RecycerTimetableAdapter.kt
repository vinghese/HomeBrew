package edu.ddukk.homebrew.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.ddukk.homebrew.R
import edu.ddukk.homebrew.data.TimeTableView

class RecycerTimetableAdapter(val list: List<TimeTableView>) :
    RecyclerView.Adapter<RecyclerView
    .ViewHolder>() {

    private lateinit var parent: ViewGroup

    private val ITEM_TYPE = 1
    private val PROGRESS_TYPE = 2

    var isLoading: Boolean = false


    inner class ItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val txtPeriodNo: TextView = itemView.findViewById(R.id.tvPeriodNumber)
        val tvPeriodStart: TextView = itemView.findViewById(R.id.tvPeriodStart)
        val tvPeriodEnd: TextView = itemView.findViewById(R.id.tvPeriodEnd)
        val tvPeriodSubject: TextView = itemView.findViewById(R.id.tvPeriodSubject)
        val tvPeriodFaculty: TextView = itemView.findViewById(R.id.tvPeriodFaculty)


    }

    inner class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {

        return if (viewType == ITEM_TYPE) {
            this.parent = parent
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.timetable_card,
                parent, false
            )
            ItemsViewHolder(view)
        } else {
            this.parent = parent
            var view = LayoutInflater.from(parent.context).inflate(
                R.layout.progress_card,
                parent, false
            )
            ProgressViewHolder(view)
        }

//        this.parent = parent
//        val view = LayoutInflater.from(parent.context).inflate(
//            R.layout.timetable_card,
//            parent, false
//        )
//
//        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemsViewHolder) {
            val item = list[position]
            holder.txtPeriodNo.text = item.period
            holder.tvPeriodFaculty.text = item.instructor_name
            holder.tvPeriodEnd.text = item.end_time
            holder.tvPeriodStart.text = item.start_time
            holder.tvPeriodSubject.text = item.subject_name
        }
//        else if (holder is ProgressViewHolder)
//            holder.progressBar.foregroundGravity = Gravity.CENTER
    }

//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val item = list[position]
//
//        holder.txtPeriodNo.text = item.period
//        holder.tvPeriodFaculty.text = item.instructor_name
//        holder.tvPeriodEnd.text = item.end_time
//        holder.tvPeriodStart.text = item.start_time
//        holder.tvPeriodSubject.text = item.subject_name
//
//    }

    override fun getItemCount(): Int {
        return if (isLoading)
            list.size + 1
        else
            list.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoading && position == list.size) {
            PROGRESS_TYPE
        } else {
            ITEM_TYPE
        }
    }
}