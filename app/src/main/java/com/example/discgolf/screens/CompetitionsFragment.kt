package com.example.discgolf.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.discgolf.R
import com.example.discgolf.adapter.CompetitionsAdapter
import com.example.discgolf.base.BaseFragment
import com.example.discgolf.room.competitions.Competitions
import com.example.discgolf.viewmodel.CompetitionsViewModel
import kotlinx.android.synthetic.main.fragment_competitions.*
import kotlinx.android.synthetic.main.fragment_players.floatingActionButton

class CompetitionsFragment: BaseFragment() {
    override val layout: Int = R.layout.fragment_competitions

    private lateinit var competitionsList: LiveData<List<Competitions>>
    private lateinit var competitionsAdapter: CompetitionsAdapter
    private lateinit var competitionsVM: CompetitionsViewModel

    override fun subscribeUi() {

        //RecyclerView:
        recylerViewCompetitions.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recylerViewCompetitions.setHasFixedSize(true)

        //ViewModel: -> podpinamy viewmodel do fragmentu !!-> pamitaj ze viewmodel zawsze uruchamiamy w aktywnosci!!!
        competitionsVM = ViewModelProvider(this)[CompetitionsViewModel::class.java]

        //a potem zwracamy naszych wszystkich ludzi metoda
        competitionsList = competitionsVM.getAllCompetitions()
        //nastepnie obserwujemy i mowimy co ma sie dziac, jezeli ulegna zmianie elementy w naszej liscie
        competitionsList.observe(this, Observer { competitionList ->

            //playerAdapter.update(player)

            competitionsAdapter = CompetitionsAdapter(competitionList)

            //jesli lista nie jest pusta to wysyla adapter, a anstepnie
            recylerViewCompetitions.adapter = competitionsAdapter //ten adapter podpianam pod recyclerView

        })

        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_competitionsFragment_to_competitionsAddFragment)
        }

    }

    override fun unsubscribeUi() {

    }
}