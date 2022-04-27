package com.example.discgolf.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.discgolf.R
import com.example.discgolf.room.player.Player
import com.example.discgolf.viewmodel.PlayerViewModel

class PlayerAdapter(private val playerList: List<Player>,
                    private val onDelete: (Player) -> Unit): //wprowadzilem parametr o nzawie delete, ktory
    RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {      //ma wsobie playera i zwraca nic(unit lub void)
                                                            //mosze go we fragmnciee okeslic co robi...

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)

        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int = playerList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val player = playerList[position]

        holder.name.text = player.player

        holder.playerItem.setOnLongClickListener {
            onDelete(player)
            return@setOnLongClickListener true
        }
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val name: TextView = view.findViewById(R.id.nameView)
        val playerItem = view.findViewById<LinearLayout>(R.id.playerItem)


    }
}