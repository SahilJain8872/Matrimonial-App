package com.example.matrimonialapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.matrimonialapp.R
import com.example.matrimonialapp.db.entity.UserEntity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UserBottomSheet(private val users: UserEntity, private var onClick: (Boolean)->Unit) : BottomSheetDialogFragment() {

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.tvUsername).apply {
            text = "${users.firstName} ${users.lastName}"
        }

        view.findViewById<TextView>(R.id.tvAge).apply {
            text = "Age: ${users.age}"
        }
        view.findViewById<TextView>(R.id.tvGender).apply {
            text = "Gender: ${users.gender}"
        }
        view.findViewById<TextView>(R.id.tvAddress).apply {
            text = "Address: ${users.city}, ${users.state}, ${users.country}, ${users.postcode}"
        }
        view.findViewById<TextView>(R.id.tvEmail).apply {
            text = "Email: ${users.email}"
        }

        view.findViewById<TextView>(R.id.tvAccept).setOnClickListener {
            onClick.invoke(true)
            dismiss()
        }
        view.findViewById<TextView>(R.id.tvDecline).setOnClickListener {
            onClick.invoke(false)
            dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_bottom_sheet, container, false)
    }

}