package com.example.calmatemvvm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.calmatemvvm.R
import com.example.calmatemvvm.model.MeditationHolder

class MeditationAdapter(private val meditationList: List<MeditationHolder>) : RecyclerView.Adapter<MeditationAdapter.MeditationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeditationViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.meditation_card, parent, false)
        return MeditationViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeditationViewHolder, position: Int) {
        val meditation = meditationList[position]
        holder.bind(meditation)
    }

    override fun getItemCount(): Int {
        return meditationList.size
    }

    inner class MeditationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleMeditation: TextView = itemView.findViewById(R.id.titleMeditation)
        private val durationMeditation: TextView = itemView.findViewById(R.id.durMediatation)
        private val btnStartMeditation: Button = itemView.findViewById(R.id.btnStartMeditation)
        private val iconLike: CheckBox = itemView.findViewById(R.id.iconLike)
        private val imgMeditation: ImageView = itemView.findViewById(R.id.ImgMeditation)

        fun bind(meditationHolder: MeditationHolder) {
            val meditation = meditationHolder.item
            titleMeditation.text = meditation.title
            durationMeditation.text = meditation.duration.toString()
            imgMeditation.setImageResource(meditation.imageUrl)
            btnStartMeditation.setOnClickListener(meditationHolder.onClick)
        }
    }
}