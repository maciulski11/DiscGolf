package com.example.discgolf.screens

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.discgolf.R
import com.example.discgolf.adapter.PlayerAdapter
import com.example.discgolf.base.BaseFragment
import com.example.discgolf.repository.PlayerRepository
import com.example.discgolf.room.player.Player
import com.example.discgolf.viewmodel.PlayerViewModel
import kotlinx.android.synthetic.main.fragment_player_add.*
import kotlinx.android.synthetic.main.fragment_players.*

class PlayersFragment : BaseFragment() {
    override val layout: Int = R.layout.fragment_players

    private lateinit var playersList: LiveData<List<Player>>
    private lateinit var playerAdapter: PlayerAdapter
    private lateinit var playerViewModel: PlayerViewModel

    override fun subscribeUi() {

        //RecyclerView:
        recylerViewPlayers.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recylerViewPlayers.setHasFixedSize(true)

        //ViewModel: -> podpinamy viewmodel do fragmentu !!-> pamitaj ze viewmodel zawsze uruchamiamy w aktywnosci!!!
        playerViewModel = ViewModelProvider(this)[PlayerViewModel::class.java]

        //a potem zwracamy naszych wszystkich ludzi metoda
        playersList = playerViewModel.getAllPlayer()
        //nastepnie obserwujemy i mowimy co ma sie dziac, jezeli ulegna zmianie elementy w naszej liscie
        playersList.observe(this, Observer { playersList ->

            //playerAdapter.update(player)

            playerAdapter = PlayerAdapter(playersList, requireActivity(), onDelete = { player ->
                playerViewModel.deletePlayer(player) //tutaj definiuje co wykonuje parametr przyjety
            })                                    // w adapterze, czyli nasza fun delete w viewmodel

            //jesli lista nie jest pusta to wysyla adapter, a anstepnie
            recylerViewPlayers.adapter = playerAdapter //ten adapter podpianam pod recyclerView

        })

        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_playersFragment_to_playerAddFragment)
        }
    }

    override fun unsubscribeUi() {

    }
}