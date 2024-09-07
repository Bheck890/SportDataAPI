package com.bheck.sportdb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bheck.sportdb.R
import com.bheck.sportdb.SharedViewModel

class SportTeamInformationFragment : Fragment() {

    private lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        val model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        arrayAdapter =
            ArrayAdapter(
                this.activity!!.applicationContext,
                R.layout.item_layout1,
                R.id.item_text,
                model.gameEvents)

        return inflater.inflate(R.layout.team_info_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val displayM: TextView = view.findViewById(R.id.textViewTeamID)
        val listView: ListView = view.findViewById(R.id.listViewGames)
        listView.adapter = arrayAdapter

        //Display the changed variable when the variable is changed in the View Model.
        model.teamName.observe(viewLifecycleOwner, {
            displayM.text = it
        })

        model.gameUpdate.observe(viewLifecycleOwner, {
            arrayAdapter.notifyDataSetChanged()
        })
    }

}