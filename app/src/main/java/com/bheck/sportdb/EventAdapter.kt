package com.bheck.sportdb

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class EventAdapter (private val context: Context, private val arrayList: java.util.ArrayList<Events>) : BaseAdapter() {
    private lateinit var title: TextView
    private lateinit var score: TextView
    private lateinit var image: ImageView

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.game_event_item, parent, false)

        title = convertView.findViewById(R.id.title)
        score = convertView.findViewById(R.id.scores)
        image = convertView.findViewById(R.id.icon)

        title.text = " " + arrayList[position].getTitle()
        score.text = arrayList[position].getScore()
        //image.setImageResource() //Further development is put logo for the event behind it

        //Also need further Development on the Updating the system based on the array list. so that it 

        return convertView
    }

}