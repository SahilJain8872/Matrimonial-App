package com.example.matrimonialapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.matrimonialapp.R
import com.example.matrimonialapp.core.UserStatus
import com.example.matrimonialapp.db.entity.UserEntity


class MainAdapter(
    val onCardClick: (user: UserEntity)-> Unit
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var data: List<UserEntity> = ArrayList()

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

    fun submitList(data: List<UserEntity>) {
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
        fun bind(user: UserEntity) {
            itemView.findViewById<TextView>(R.id.tvUsername).apply {
                text = "${user.firstName} ${user.lastName}"
            }

            itemView.findViewById<TextView>(R.id.tvAge).apply {
                text = "${user.age} \u2022 ${user.city}, ${user.state}"
            }

            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transform(CenterCrop())
                .transform(RoundedCorners(8))

            Glide.with(itemView.context)
                .load(user.picture)
                .centerInside()
                .placeholder(R.drawable.unknown)
                .error(R.drawable.unknown)
                .apply(requestOptions)
                .into(itemView.findViewById(R.id.ivUserImage))

            setStatus(user.status)

        }

        @SuppressLint("UseCompatLoadingForDrawables")
        fun setStatus(status: String){
            val ivStatus = itemView.findViewById<ImageView>(R.id.ivUserStatus)
            when(status){
                UserStatus.NONE.toString()->{
                    ivStatus.visibility = View.GONE
                }
                UserStatus.ACCEPT.toString()-> {
                    ivStatus.setImageResource(R.mipmap.ic_user_selected)
                    ivStatus.visibility = View.VISIBLE
                }
                UserStatus.DECLINE.toString()-> {
                    ivStatus.setImageResource(R.mipmap.ic_user_rejected)
                    ivStatus.visibility = View.VISIBLE
                }
            }
        }
    }

}