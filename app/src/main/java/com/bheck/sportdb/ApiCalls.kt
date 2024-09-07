package com.bheck.sportdb

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import org.json.JSONObject

class ApiCalls {

    suspend fun getTeamID(teamName: String):ArrayList<String> {

        //function variables
        var data : ArrayList<String> = ArrayList() //Data needed from the first search

        //Reformat the Team name if it has Spaces, for the URL
        val properTeamName =
            if (teamName.contains(" ")) teamName.replace(" ", "%20")
            else teamName

        println("Input: $teamName Formatted: $properTeamName")
        val url = "https://www.thesportsdb.com/api/v1/json/3/searchteams.php?t=$properTeamName"

        // Establish connection to the API and gather the info
        val client = HttpClient()
        val httpResponse: HttpResponse = client.get(url)
        val stringBody: String = httpResponse.receive()

        //Dissect the Data to gather the Team ID number and Name
        try{
            val jsonOBJ = JSONObject(stringBody)
            val teamsArray = jsonOBJ.optJSONArray("teams")
            val firstTeam = teamsArray.optJSONObject(0)
            data.add(firstTeam.optString("idTeam"))
            data.add(firstTeam.optString("strTeam"))
        }
        catch (e: Exception){
            var empty : ArrayList<String> = ArrayList()
            empty.add("404")
            empty.add("404")
            return empty
        }

        return data
    }

    suspend fun getGameHistory(teamID: String): ArrayList<Events> {

        //function variables
        var pastGame = arrayListOf<Events>()
        val url = "https://www.thesportsdb.com/api/v1/json/3/eventslast.php?id=$teamID"

        // Establish connection to the API and gather the info
        val client = HttpClient()
        val httpResponse: HttpResponse = client.get(url)
        val stringBody: String = httpResponse.receive()

        val jsonOBJ = JSONObject(stringBody)
        val eventArray = jsonOBJ.optJSONArray("results")

        println("Games Added")
        for (i in 0..4) {
            pastGame.add(Events(eventArray.optJSONObject(i)))
        }

        return pastGame
    }
}
