package com.example.calmatemvvm.model

import android.view.View.OnClickListener

data class Meditation(
    val imageUrl: Int,
    val title: String,
    val duration: Int,
    val description: String,
    val audioUrl: Int,
    val category: String
)

data class MeditationHolder(
    val item : Meditation,
    val onClick : OnClickListener
)
