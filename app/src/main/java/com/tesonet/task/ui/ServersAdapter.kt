package com.tesonet.task.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.tesonet.task.R
import com.tesonet.task.repository.entity.ServersEntity
import kotlinx.android.synthetic.main.servers_list_item.view.*

class ServersAdapter : RecyclerView.Adapter<ServersAdapter.ServersItemViewHolder>() {

    private var data = emptyList<ServersEntity>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ServersItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.servers_list_item, parent, false)
        return ServersItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServersItemViewHolder, position: Int) {
        val server = data[position]

        holder.serverTitleView.text = server.name
        holder.rootView.setOnClickListener {
            //todo navigate
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ServersItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var rootView: ConstraintLayout = view.root
        var serverTitleView: TextView = view.serverTitle
    }

    fun updateData(data: List<ServersEntity>) {
        this.data = data
        notifyDataSetChanged()
    }
}