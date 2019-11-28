package com.example.ppm

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.parse.ParseObject
import com.parse.ParseQuery
import org.jetbrains.anko.find
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private lateinit var gameLayout: ConstraintLayout
    private lateinit var gameText : TextView
    private lateinit var gameType : TextView
    private lateinit var arrowBack : TextView
    lateinit var games: MutableList<ParseObject>

    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        supportActionBar?.hide()

        //Finds
        gameText = find(R.id.textGame)
        gameType = find(R.id.typeOfGame)
        arrowBack = find(R.id.backArrow)

        //Layout Clicks
        gameLayout = find(R.id.gameLayout_layout)



        games = mutableListOf<ParseObject>()



        val query = ParseQuery.getQuery<ParseObject>("Games")
        games = query.find()
        Log.d("cagadero", games.get(1).get("Game").toString())

        //Este es un caso hard code
        gameLayout.setBackgroundColor(Color.parseColor("#3A7A09"))
        gameText.text = games.get(1).get("Game").toString()
        //---------

       // val sharedPref = getSharedPreferences("session", Context.MODE_PRIVATE)
        val query1 = ParseQuery.getQuery<ParseObject>("Players")
        query1.orderByDescending("createdAt")
        var playersString = query1.first
        Log.d("players 1 ", playersString.get("Players").toString())


        val players = playersString.get("Players").toString().split(", ")
        Log.d("players 2", players.toString())

        gameLayout.setOnClickListener {
            count = games.size
                val game = games.get(Random.nextInt(0, count-1))
                Log.d("Listener", game.get("Type").toString())
                var texto: String

                if (game.get("PlayerOrder") == 1) {
                    texto = players.get(Random.nextInt(0,  players.size-1)).toString().plus(" ").plus(game.get("Game").toString())
                    //texto = game.get("Game").toString()
                } else {
                    texto = game.get("Game").toString()
                }

                when (game.get("Type").toString()) {
                    "Games" -> {
                        gameLayout.setBackgroundColor(Color.parseColor("#3A7A09"))
                        gameType.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_add_circle,
                            0,
                            0,
                            0
                        )
                        gameType.text = "Game"
                        gameText.text = texto
                    }
                    "Rules" -> {
                        gameLayout.setBackgroundColor(Color.parseColor("#CC3704"))
                        gameType.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_arrow_back,
                            0,
                            0,
                            0
                        )
                        gameType.text = "Rule"
                        gameText.text = texto
                    }
                    "Challenges" -> {
                        gameLayout.setBackgroundColor(Color.parseColor("#293AA1"))
                        gameType.setCompoundDrawablesWithIntrinsicBounds(
                            R.drawable.ic_arrow_yellow_24dp,
                            0,
                            0,
                            0
                        )
                        gameType.text = "Challenge"
                        gameText.text = texto
                    }
                }
        }

        //BackArrow
        arrowBack.setOnClickListener {
            onBackPressed()
        }
    }
}