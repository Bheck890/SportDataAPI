package com.bheck.sportdb

import org.json.JSONObject

class Events  {

    private var title:String = " "
    private var date:String = " "
    private var homeTeam:String = " "
    private var awayTeam:String = " "
    private var homeScore:String = " "
    private var awayScore:String = " "
    private var eventLogo:String = " "
    private var mainTeam:String = " "

    constructor(obj : JSONObject){
        title = obj.optString("strEvent")
        date = obj.optString("dateEvent")
        homeTeam = obj.optString("strHomeTeam")
        awayTeam = obj.optString("strAwayTeam")
        homeScore = obj.optString("intHomeScore")
        awayScore = obj.optString("intAwayScore")
        eventLogo = obj.optString("strThumb")

        if(homeScore == "null"){
            homeScore = "Practice"
            awayScore = "Practice"
        }
    }

    @JvmName("setMainTeam1")
    fun setMainTeam(teamName:String) {
        mainTeam = teamName
    }

    private fun getTeams(): String {
        return if(mainTeam == homeTeam)
            "*$homeTeam - $awayTeam\n"
        else
            "$homeTeam - *$awayTeam\n"
    }

    fun getTitle(): String { //Used in toString and the custom ArrayAdapter
        return title
    }

    fun getScore(): String { //Used in toString and the custom ArrayAdapter
        return "$homeScore - $awayScore"
    }

    override fun toString(): String {
        return "$date\n" +
                "${getTeams()}" +
                "${getScore()}"
    }

}