package iteso.mx.fragments.fragments

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
import com.example.ppm.MyAdapter
import com.example.ppm.R
import com.google.android.material.textfield.TextInputEditText
import com.parse.ParseObject
import com.parse.ParseQuery


class FragmentGame : Fragment() {
    //XML Elements
    lateinit var newPlayer: TextInputEditText
    lateinit var players: MutableList<String>
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

    lateinit var user: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game, container, false)
        players = mutableListOf<String>()
        viewAdapter = MyAdapter(players)
        viewManager = LinearLayoutManager(view.context)
        recyclerView = view.findViewById<RecyclerView>(R.id.fragment_game_rv_usersText)
        newPlayer = view.findViewById(R.id.fragment_game_ti_newPlayer_tx)
        addPlayer = view.findViewById(R.id.fragment_game_iv_addPlayer)
        goBack = view.findViewById(R.id.fragment_game_i_backArrow)
        saveBtn = view.findViewById(R.id.fragment_game_btn_save)
        playBtn = view.findViewById(R.id.fragment_game_btn_play)
        newGroup = view.findViewById(R.id.fragment_game_ti_newGroup_tx)
        return view
    }

    override fun onActivityCreated(state: Bundle?) {
        super.onActivityCreated(state)

        val sharedPref = this.getActivity()?.getSharedPreferences("session", Context.MODE_PRIVATE)
        user = sharedPref!!.getString("user", "no jala")!!

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
        addPlayer.setOnClickListener(View.OnClickListener {
            players.add(playersCount, newPlayer.text.toString())
            playersCount++
            newPlayer.text?.clear()
            Log.d("array", players[players.size - 1])
            viewAdapter.notifyDataSetChanged()
        })

        goBack.setOnClickListener(View.OnClickListener {
            this.getActivity()?.onBackPressed()
        })

        saveBtn.setOnClickListener(View.OnClickListener {
            Log.d("Entra boton save", "Entra  boton save")

            val query = ParseQuery.getQuery<ParseObject>("UserPPM")
            query.findInBackground { objects, e ->
                if (e == null) {
                    for (armor in objects) {
                        if (armor.get("username") == user) {
                            Log.d("back", armor.get("Groupos").toString())
                            val aux =
                                armor.get("Groupos").toString().plus("/")
                                    .plus(newGroup.text.toString()).plus(players.toString())
                            armor.put("Groupos", aux)
                            armor.saveInBackground()
                            Log.d("array", aux)
                        }
                    }
                } else {
                    Log.d("Errorsazo", "Error: " + e!!.message)
                }
            }
        })

        playBtn.setOnClickListener(View.OnClickListener {
            Log.d("Entra el game", "game: ")

            val editor = sharedPref.edit()
            editor.putString("playersString", players.toString())
            editor.apply()

            val intent = Intent(activity, GameActivity::class.java)
            activity?.startActivity(intent)
        })
    }
}