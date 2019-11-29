package com.example.ppm.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ppm.GameActivity
import com.example.ppm.GroupsAdapter
import com.example.ppm.MyAdapter
import com.example.ppm.R
import com.google.android.material.textfield.TextInputEditText
import com.parse.ParseObject
import com.parse.ParseQuery

class FragmentGroups: Fragment() {
    lateinit var newPlayer: TextInputEditText
    lateinit var players:  MutableList<Array<String>>
    lateinit var addPlayer: ImageView
    lateinit var goBack: ImageView
    lateinit var saveBtn: Button
    lateinit var playBtn: Button
    lateinit var newGroup: TextInputEditText

    //Playerscount variable
    var playersCount = 0

    //Recycledview elements
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    var plyrs = ""

    lateinit var user: String



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_groups, container, false)
        players =  mutableListOf<Array<String>>()
        viewAdapter = GroupsAdapter(players)
        viewManager = LinearLayoutManager(view.context) as RecyclerView.LayoutManager
        recyclerView = view.findViewById<RecyclerView>(R.id.my_recycler_view)
        goBack = view.findViewById(R.id.fragment_group_iv_backArrow)
        playBtn = view.findViewById(R.id.fragmen_group_btn_play)

        return view
    }

    override fun onActivityCreated(state: Bundle?) {
        super.onActivityCreated(state)

        val sharedPref = this.getActivity()?.getSharedPreferences("session", Context.MODE_PRIVATE)
        user = sharedPref!!.getString("user", "no jala")!!
        Log.d("Grupos", user)

        val query = ParseQuery.getQuery<ParseObject>("UserPPM")
        var users = query.find()


        var groupsSrt = ""

        users.forEach { u -> run{
            if (u.get("username").toString().equals(user)) {
                groupsSrt = u.get("Groupos").toString()
            }
        }}


        var groupsArray = groupsSrt.split("/")


        groupsArray.forEach { grp -> run{
            var grpSplitted = grp.split("[")
            //players.add(playersCount, grpSplitted[0])

            players.add(playersCount, grpSplitted.toTypedArray())
            Log.d("new data", grpSplitted.toString())
            playersCount++
        }}

        //Log.d("Y", players[1].get(0))
        //Log.d("X", players[0][0])
       // Log.d("Grupos", groupsArray.toString())
        //Recycler View

        recyclerView.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter




        }



        goBack.setOnClickListener(View.OnClickListener {
            this.getActivity()?.onBackPressed()
        })



        playBtn.setOnClickListener(View.OnClickListener{
            Log.d("Entra el game", "game: ")
            val editor = sharedPref.edit()
            editor.putString("playersString", players.toString())
            editor.apply()

            val intent = Intent(activity, GameActivity::class.java)
            activity?.startActivity(intent)
        })
    }
}