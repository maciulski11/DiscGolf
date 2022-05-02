package com.example.discgolf.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.discgolf.R
import com.example.discgolf.viewmodel.CompetitionsViewModel
import com.example.discgolf.viewmodel.PlayerViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModelP: PlayerViewModel
    private lateinit var viewModelC: CompetitionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //!!viewmodel tworzymy zawsze w aktywnosci!!!
        //nastepnie podpinamy nasz viewModel:
        viewModelP = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application)
            .create(PlayerViewModel::class.java)

        viewModelC = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(application)
            .create(CompetitionsViewModel::class.java)

        //po kliknieciu w actionBar pijawi nam sie we fragmencie przycisk do cofniecia
        setupActionBarWithNavController(findNavController(R.id.fragment))
    }
    //dzieki tej fun mozemy sie cofnac tym co wywolalismy wyzej
    override fun onSupportNavigateUp(): Boolean {
        val navController= findNavController(R.id.fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}