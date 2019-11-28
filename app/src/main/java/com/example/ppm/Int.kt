package com.example.ppm

import android.view.View

interface RecyclerViewClickListener {
    fun recyclerViewListClicked(v: View, position: Int)
}