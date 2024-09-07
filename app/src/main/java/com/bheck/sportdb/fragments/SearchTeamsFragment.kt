package com.bheck.sportdb.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.bheck.sportdb.R
import com.bheck.sportdb.SharedViewModel

class SearchTeamsFragment : Fragment() {

    private lateinit var model: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.team_search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button: Button = view.findViewById(R.id.buttonSearch)
        val textMessage: EditText = view.findViewById(R.id.editTextTextPersonName)

        model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        button.setOnClickListener {
            model.searchTeamName(textMessage.text.toString())
        }
    }

}