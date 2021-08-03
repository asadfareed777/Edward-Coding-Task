package com.asad.fareed.task.ui.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.asad.fareed.task.R
import com.asad.fareed.task.data.api.ApiHelper
import com.asad.fareed.task.data.api.RetrofitBuilder
import com.asad.fareed.task.data.model.User
import com.asad.fareed.task.databinding.ActivityMainBinding
import com.asad.fareed.task.ui.base.ViewModelFactory
import com.asad.fareed.task.ui.main.viewmodel.MainViewModel
import com.asad.fareed.task.utils.Status.ERROR
import com.asad.fareed.task.utils.Status.LOADING
import com.asad.fareed.task.utils.Status.SUCCESS

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_home) as NavHostFragment
        navController = navHostFragment.navController
    }
}
