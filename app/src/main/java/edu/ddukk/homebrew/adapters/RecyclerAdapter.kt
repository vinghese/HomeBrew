package edu.ddukk.homebrew.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import edu.ddukk.homebrew.data.RecycerDataItem
import edu.ddukk.homebrew.R

class RecyclerAdapter(val list : List<RecycerDataItem>) : RecyclerView
    .Adapter<RecyclerAdapter
    .ViewHolder>() {

    private lateinit var parent: ViewGroup

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgView : ImageView = itemView.findViewById(R.id.imgRecyerCardIcon)
        val tetView : TextView = itemView.findViewById(R.id.tvRecycerCardItem)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        this.parent = parent
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_view,
            parent, false)

        return  ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.imgView.setImageResource(item.image)
        holder.tetView.text = item.dept

        holder.tetView.setOnClickListener{
            Toast.makeText(parent.context, list[position].dept+"Clicked", Toast.LENGTH_LONG).show()
        }

    }

}
