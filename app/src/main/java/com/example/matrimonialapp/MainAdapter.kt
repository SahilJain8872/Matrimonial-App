package com.example.matrimonialapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.matrimonialapp.network.Model

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var data: ArrayList<Model.Users.Results> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun submitList(data: ArrayList<Model.Users.Results>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
//                onCardlessBankSelect.invoke(data[adapterPosition])
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(user: Model.Users.Results) {
            itemView.findViewById<TextView>(R.id.tvUsername).apply {
                text = "${user.name?.first ?: ""} ${user.name?.last ?: ""}"
            }

            itemView.findViewById<TextView>(R.id.tvAge).apply {
                text = "${user.dob?.age ?: ""} \u2022 ${user.location?.city ?: ""}, ${user.location?.state}"
            }

            Glide.with(itemView.context)
                .load(user.picture?.large)
                .centerInside()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(itemView.findViewById(R.id.ivUserImage))
        }
    }

}