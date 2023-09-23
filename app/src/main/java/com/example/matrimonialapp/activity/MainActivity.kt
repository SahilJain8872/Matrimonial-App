package com.example.matrimonialapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.matrimonialapp.R
import com.example.matrimonialapp.Response
import com.example.matrimonialapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private var viewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setUpObserver()
        viewModel?.getUsers(100)

    }

    private fun setUpObserver(){
        viewModel?.usersList?.observe(this, Observer { data ->
            when (data.status) {
                Response.Status.LOADING -> {
                    println("Loading")
                }
                Response.Status.SUCCESS -> {
                    println("Success")
                }
                Response.Status.ERROR -> {
                    println("Fail")
                }
            }
        })
    }
}