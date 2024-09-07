package com.bheck.sportdb


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

    val teamName = MutableLiveData<String>()
    val gameUpdate = MutableLiveData<String>() //to update the ListView

    private var pastGames: ArrayList<Events> = ArrayList()
    val gameEvents: ArrayList<String> = ArrayList() //Data the Default Array list is looking at it

    //Update call from the search Fragment, to gather information
    fun searchTeamName(teamName: String) {

        val apiCall = ApiCalls()
        println("Start Search")

        gameEvents.clear()
        this.teamName.value = "Searching"

        //Needs to wait for API to respond
        viewModelScope.launch {
            val data = apiCall.getTeamID(teamName)
            val teamID = data[0]
            val teamNameAPI = data[1]

            println("TeamID: $teamID")

            if (teamID == "404")
                this@SharedViewModel.teamName.value =
                    "Unable to find team check spelling and try again\n" +
                        "it needs to be the exact name \"{City} {Team Name}\""
            else {
                this@SharedViewModel.teamName.value = teamNameAPI

                viewModelScope.launch {
                    pastGames = apiCall.getGameHistory(teamID)
                    pastGames.forEach { event ->
                        event.setMainTeam(teamNameAPI)
                    }

                    for (item in pastGames) {
                        gameUpdate.value = item.toString()
                        gameEvents.add(item.toString())
                    }

                }
            }
        }
    }

}
