package com.example.matrimonialapp.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matrimonialapp.MainAdapter
import com.example.matrimonialapp.R
import com.example.matrimonialapp.Response
import com.example.matrimonialapp.UserBottomSheet
import com.example.matrimonialapp.network.Model
import com.example.matrimonialapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private var viewModel: MainViewModel? = null
    private lateinit var mainAdapter: MainAdapter

    companion object{
        lateinit var appContext: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainAdapter = MainAdapter { user ->
            showBottomSheet(user)
        }
        findViewById<RecyclerView>(R.id.rvUsers).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }

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
                    mainAdapter.submitList(data.data?.results ?: arrayListOf())
                    println("Success")
                }
                Response.Status.ERROR -> {
                    println("Fail")
                }
            }
        })
    }
    private fun showBottomSheet(user: Model.Users.Results){
        UserBottomSheet(user) { isAccepted ->
            if(isAccepted){
                // TODO: sync accepted status in db
            }else{
                // TODO: sync decline status in db
            }
        }.show(supportFragmentManager, "bottom")
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        appContext = this
    }
}