package com.example.discgolf.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.discgolf.R
import com.example.discgolf.dialogs.DeleteDialogFragment
import com.example.discgolf.room.player.Player

class PlayerAdapter(private var playerList: List<Player>, val activity: FragmentActivity,
                    private val onDelete: (Player) -> Unit): //wprowadzilem parametr o nzawie delete, ktory
    RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {      //ma wsobie playera i zwraca nic(unit lub void)
                                                            //mozesz go we fragmnciee okeslic co robi...

    fun update(fresPlayer: List<Player>) {
        playerList = fresPlayer
        notifyDataSetChanged()
    }

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
//            onDelete(player)
            DeleteDialogFragment(action = {
                onDelete(player)
            }).show(activity)
            true
        }
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val name: TextView = view.findViewById(R.id.nameView)
        val playerItem = view.findViewById<LinearLayout>(R.id.playerItem)


    }
}