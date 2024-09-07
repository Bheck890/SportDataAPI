package com.bheck.sportdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bheck.sportdb.fragments.*
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // Create transaction Fragment to add multiple fragments to the activity
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.search_team_container,SearchTeamsFragment())
        transaction.add(R.id.team_information_container,SportTeamInformationFragment())
        transaction.commit()
    }
}