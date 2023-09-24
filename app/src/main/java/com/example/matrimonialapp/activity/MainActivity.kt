package com.example.matrimonialapp.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.matrimonialapp.DBManager
import com.example.matrimonialapp.MainAdapter
import com.example.matrimonialapp.R
import com.example.matrimonialapp.Response
import com.example.matrimonialapp.UserBottomSheet
import com.example.matrimonialapp.db.entity.UserEntity
import com.example.matrimonialapp.network.Model
import com.example.matrimonialapp.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

        CoroutineScope(Dispatchers.IO).launch {
            DBManager.insertUser(UserEntity("12345","Sahil","Jain","25","Male","af","dfasfd","dasfdaf","342433","sid@8872","Single"))
            DBManager.getUsersList()
        }
    }

    private fun setUpObserver(){
        viewModel?.usersList?.observe(this, Observer { data ->
            when (data.status) {
                Response.Status.LOADING -> {
                    showLoader(true)
                }
                Response.Status.SUCCESS -> {
                    showLoader(false)
                    if(data.data?.results.isNullOrEmpty()){
                        showError()
                    }else{
                        mainAdapter.submitList(data.data?.results ?: arrayListOf())
                    }
                }
                Response.Status.ERROR -> {
                    showLoader(false)
                    showError()
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

    private fun showLoader(show: Boolean){
        findViewById<LottieAnimationView>(R.id.loader).visibility = if (show){
            View.VISIBLE
        }else{
            View.GONE
        }
    }

    private fun showError(){
        findViewById<RecyclerView>(R.id.rvUsers).visibility = View.GONE
        findViewById<Group>(R.id.group_error).visibility = View.VISIBLE
    }
}