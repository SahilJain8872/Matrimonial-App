package com.example.matrimonialapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.matrimonialapp.network.Model
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UserBottomSheet(val users: Model.Users.Results) : BottomSheetDialogFragment() {

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.tvUsername).apply {
            text = "${users.name?.first ?: ""} ${users.name?.last ?: ""}"
        }

        view.findViewById<TextView>(R.id.tvAge).apply {
            text = "Age: ${users.dob?.age}"
        }
        view.findViewById<TextView>(R.id.tvGender).apply {
            text = "Gender: ${users.gender}"
        }
        view.findViewById<TextView>(R.id.tvAddress).apply {
            text = "Address: ${users.location?.city ?: ""}, ${users.location?.state}, ${users.location?.country ?: ""}, ${users.location?.postcode}"
        }
        view.findViewById<TextView>(R.id.tvEmail).apply {
            text = "Email: ${users.email}"
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_bottom_sheet, container, false)
    }

}