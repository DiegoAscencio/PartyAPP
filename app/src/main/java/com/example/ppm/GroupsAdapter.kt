package com.example.ppm

import android.content.ClipData
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.parse.ParseObject
import kotlinx.android.synthetic.main.players_viewholder.view.*

class GroupsAdapter(private val myDataset: MutableList<Array<String>>) :
        RecyclerView.Adapter<GroupsAdapter.MyViewHolder>() {

    public var itemSelected = 0

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class MyViewHolder(val textView: LinearLayout) : RecyclerView.ViewHolder(textView)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): GroupsAdapter.MyViewHolder {
        // create a new view
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.players_viewholder, parent, false) as LinearLayout

        return MyViewHolder(textView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.textView.players_vh_text.text = myDataset[position][0]


        holder.textView.setOnClickListener {


            itemSelected = position-1

            var players = myDataset[position][1].split(",")
            var last = players.last().dropLast(1)
            //players[players.size-1].dropLast(1)
            players =  players.dropLast(1)
            players += last
            Log.d("Dataset", myDataset[position][1])
            Log.d("Players", players.toString())
            //listener(players.toString())
            //val intent = Intent(context.getSystemService, GameActivity::class.java)

            val gameScore = ParseObject("Players")
            gameScore.put("Players", players.toString())
            gameScore.save()
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = myDataset.size



}