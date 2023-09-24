package com.example.matrimonialapp.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Group
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matrimonialapp.R
import com.example.matrimonialapp.adapter.MainAdapter
import com.example.matrimonialapp.core.Response
import com.example.matrimonialapp.core.UserStatus
import com.example.matrimonialapp.databinding.ActivityMainBinding
import com.example.matrimonialapp.db.entity.UserEntity
import com.example.matrimonialapp.fragment.UserBottomSheet
import com.example.matrimonialapp.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var mainAdapter: MainAdapter
    private lateinit var binding: ActivityMainBinding

    companion object{
        lateinit var appContext: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainAdapter = MainAdapter { user ->
            showBottomSheet(user)
        }
       binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = mainAdapter
        }

        setUpObserver()

        viewModel.getUsers(13)
    }

    private fun setUpObserver(){
        viewModel.usersList.observe(this, Observer { data ->
            when (data.status) {
                Response.Status.LOADING -> {
                    showLoader(true)
                }
                Response.Status.SUCCESS -> {
                    showLoader(false)
                    if(data.data.isNullOrEmpty()){
                        showError()
                    }else{
                        mainAdapter.submitList(data.data)
                    }
                }
                Response.Status.ERROR -> {
                    showLoader(false)
                    showError()
                }
            }
        })
    }
    private fun showBottomSheet(user: UserEntity){
        UserBottomSheet(user) { isAccepted ->
            if(isAccepted){
                viewModel.updateUser(user.apply { this.status = UserStatus.ACCEPT.toString() })
            }else{
                viewModel.updateUser(user.apply { this.status = UserStatus.DECLINE.toString() })
            }
        }.show(supportFragmentManager, "bottom")
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        appContext = this
    }

    private fun showLoader(show: Boolean){
        binding.loader.visibility = if (show){
            View.VISIBLE
        }else{
            View.GONE
        }
    }

    private fun showError(){
        binding.rvUsers.visibility = View.GONE
        binding.groupError.visibility = View.VISIBLE
    }
}