package com.example.matrimonialapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.matrimonialapp.R
import com.example.matrimonialapp.network.Model


class MainAdapter(
    val onCardClick: (user: Model.Users.Results)-> Unit
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

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
                onCardClick.invoke(data[adapterPosition])
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

            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transform(CenterCrop())
                .transform(RoundedCorners(8))

            Glide.with(itemView.context)
                .load(user.picture?.large)
                .centerInside()
                .placeholder(R.drawable.unknown)
                .error(R.drawable.unknown)
                .apply(requestOptions)
                .into(itemView.findViewById(R.id.ivUserImage))
        }
    }

}