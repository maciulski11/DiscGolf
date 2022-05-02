package com.example.discgolf.screens

import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.discgolf.R
import com.example.discgolf.adapter.PlayerAdapter
import com.example.discgolf.base.BaseFragment
import com.example.discgolf.room.competitions.Competitions
import com.example.discgolf.room.player.Player
import com.example.discgolf.viewmodel.CompetitionsViewModel
import com.example.discgolf.viewmodel.PlayerViewModel
import kotlinx.android.synthetic.main.fragment_competitions_add.*
import kotlinx.android.synthetic.main.fragment_competitions_add.view.*
import kotlinx.android.synthetic.main.fragment_player_add.*
import kotlinx.android.synthetic.main.fragment_players.*

class CompetitionsAddFragment: BaseFragment() {
    override val layout: Int = R.layout.fragment_competitions_add

    private val competitionsViewModel: CompetitionsViewModel by viewModels()

    private lateinit var playersList: LiveData<List<Player>>
    private lateinit var playerAdapter: PlayerAdapter
    private lateinit var playerViewModel: PlayerViewModel

    override fun subscribeUi() {

        startBT.setOnClickListener {
            insertDataToDatabase()
        }

        playersList()
        amountHoles()

    }

    private fun insertDataToDatabase() {

        val name = nameCompetitions.text.toString()
        val amount = amountTV.text.toString()

        if(inputCheck(name)){
            //tworzymy objekt gracza
            val competitions = Competitions(name, amount.toInt())

            //dodajemy dane do bazydanych
            competitionsViewModel.addCompetitions(competitions)

            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()

            //navigate back -> wroc do listy zawodow
            findNavController().navigate(R.id.action_competitionsAddFragment_to_competitionsFragment)
        } else{
            Toast.makeText(requireContext(), "Please fill out name", Toast.LENGTH_SHORT).show()
        }
    }

    private fun amountHoles(){
        var i = 0

        plusBT.setOnClickListener {
            if (i == 25){
                onStop()
            } else{
                i ++
                amountTV.text = "$i"
            }
        }
        minusBT.setOnClickListener {
            if (i == 0){
                onStop()
            } else{
                i --
                amountTV.text = "$i"
            }
        }
    }

    //fun sprawdza czy dane (name) nie sa puste
    private fun inputCheck(name: String): Boolean{
        return !(TextUtils.isEmpty(name))
    }

    private fun playersList(){

        recyclerViewPlayers.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerViewPlayers.setHasFixedSize(true)

        //ViewModel: -> podpinamy viewmodel do fragmentu !!-> pamitaj ze viewmodel zawsze uruchamiamy w aktywnosci!!!
        playerViewModel = ViewModelProvider(this)[PlayerViewModel::class.java]

        //a potem zwracamy naszych wszystkich ludzi metoda
        playersList = playerViewModel.getAllPlayer()
        //nastepnie obserwujemy i mowimy co ma sie dziac, jezeli ulegna zmianie elementy w naszej liscie
        playersList.observe(this, Observer { playersList ->

            playerAdapter = PlayerAdapter(playersList, requireActivity(), onDelete = { player ->
                playerViewModel.deletePlayer(player) //tutaj definiuje co wykonuje parametr przyjety
            })                                    // w adapterze, czyli nasza fun delete w viewmodel

            //jesli lista nie jest pusta to wysyla adapter, a anstepnie
            recyclerViewPlayers.adapter = playerAdapter //ten adapter podpianam pod recyclerView

        })
    }


    override fun unsubscribeUi() {

    }
}