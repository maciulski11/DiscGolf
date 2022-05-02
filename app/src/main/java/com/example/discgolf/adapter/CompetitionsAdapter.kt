package com.example.discgolf.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.discgolf.R
import com.example.discgolf.room.competitions.Competitions

class CompetitionsAdapter (private var competitionsList: List<Competitions>)
    : RecyclerView.Adapter<CompetitionsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionsAdapter.ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_competitions, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CompetitionsAdapter.ViewHolder, position: Int) {

        val competitions = competitionsList[position]

        holder.name.text = competitions.nameCompetitions
        holder.holes.text = competitions.amountHoles.toString()

    }

    override fun getItemCount(): Int = competitionsList.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val name: TextView = view.findViewById(R.id.nameCompetitions)
        val holes: TextView = view.findViewById(R.id.holesAmountTV)

    }

}