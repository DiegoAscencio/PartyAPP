package com.example.ppm

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import org.jetbrains.anko.find

class GameActivity : AppCompatActivity() {

    private lateinit var gameLayout: ConstraintLayout
    private lateinit var gameText : TextView
    private lateinit var gameType : TextView
    private lateinit var arrowBack : TextView
    private var clicks = 0

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

        //Este es un caso hard code
        gameLayout.setBackgroundColor(Color.parseColor("#3A7A09"))
        gameText.text = "Text of game"
        clicks = 1
        //---------

        gameLayout.setOnClickListener {
            when (clicks) {
                0 -> {
                    gameLayout.setBackgroundColor(Color.parseColor("#3A7A09"))
                    gameType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add_circle,0,0,0)
                    gameType.text = "Game"
                    gameText.text = "Text of game"
                    clicks = 1
                }
                1 -> {
                    gameLayout.setBackgroundColor(Color.parseColor("#CC3704"))
                    gameType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow_back,0,0,0)
                    gameType.text = "Rule"
                    gameText.text = "Text of rule"
                    clicks = 2
                }
                2->{
                    gameLayout.setBackgroundColor(Color.parseColor("#293AA1"))
                    gameType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_arrow_yellow_24dp,0,0,0)
                    gameType.text = "Challenge"
                    gameText.text = "Text of challenge"
                    clicks = 0
                }
            }
            Log.d("Click", "Layout tocuh made")
        }

        //BackArrow

        arrowBack.setOnClickListener {
            onBackPressed()
        }
    }
}