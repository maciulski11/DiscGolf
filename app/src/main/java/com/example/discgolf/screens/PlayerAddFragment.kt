package com.example.discgolf.screens

import android.text.TextUtils
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.discgolf.R
import com.example.discgolf.base.BaseFragment
import com.example.discgolf.room.player.Player
import com.example.discgolf.viewmodel.PlayerViewModel
import kotlinx.android.synthetic.main.fragment_player_add.*

class PlayerAddFragment: BaseFragment() {
    override val layout: Int = R.layout.fragment_player_add

    //pobierasz instancje viewmodelu czyli taki daj mi tu viewmodel
    private val playerViewModel: PlayerViewModel by viewModels()

    override fun subscribeUi() {

        addPlayerBT.setOnClickListener {
            insertDataToDatabase()
        }
    }

    fun insertDataToDatabase(){
        val name = insertName.text.toString()

        if(inputCheck(name)){
            //tworzymy objekt gracza
            val player = Player(name)

            //dodajemy dane do bazydanych
            playerViewModel.addPlayer(player)

            Toast.makeText(requireContext(), "Successfully added", Toast.LENGTH_SHORT).show()

            //navigate back -> wroc do listy graczy
            findNavController().navigate(R.id.action_playerAddFragment_to_playersFragment)
        } else{
            Toast.makeText(requireContext(), "Please fill out name", Toast.LENGTH_SHORT).show()
        }
    }

    //fun sprawdza czy dane (imie) nie sa puste
    private fun inputCheck(name: String): Boolean{
        return !(TextUtils.isEmpty(name))
    }

    override fun unsubscribeUi() {

    }
}