package com.example.matrimonialapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.matrimonialapp.databinding.FragmentUserBottomSheetBinding
import com.example.matrimonialapp.db.entity.UserEntity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UserBottomSheet(private val users: UserEntity, private var onClick: (Boolean)->Unit) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentUserBottomSheetBinding

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvUsername.apply {
            text = "${users.firstName} ${users.lastName}"
        }

        binding.tvAge.apply {
            text = "Age: ${users.age}"
        }
        binding.tvGender.apply {
            text = "Gender: ${users.gender}"
        }
        binding.tvAddress.apply {
            text = "Address: ${users.city}, ${users.state}, ${users.country}, ${users.postcode}"
        }
        binding.tvEmail.apply {
            text = "Email: ${users.email}"
        }

        binding.tvAccept.setOnClickListener {
            onClick.invoke(true)
            dismiss()
        }
        binding.tvDecline.setOnClickListener {
            onClick.invoke(false)
            dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBottomSheetBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}