package com.asad.fareed.task.ui.main.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asad.fareed.task.R
import com.asad.fareed.task.data.api.ApiHelper
import com.asad.fareed.task.data.api.RetrofitBuilder
import com.asad.fareed.task.data.model.User
import com.asad.fareed.task.databinding.FragmentHomeBinding
import com.asad.fareed.task.ui.base.ViewModelFactory
import com.asad.fareed.task.ui.main.adapter.MainAdapter
import com.asad.fareed.task.ui.main.callbacks.ItemClickHandler
import com.asad.fareed.task.ui.main.viewmodel.MainViewModel
import com.asad.fareed.task.utils.Status


class HomeFragment : Fragment(),ItemClickHandler {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MainViewModel
    private var adapter: MainAdapter? = null
    private var recyclerView:RecyclerView? = null
    private val progressBar by lazy {
        binding.progressBar
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        setupViewModel()
        setupUI()
        setupObservers()
        return binding.root
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            requireActivity(),
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        recyclerView = binding.recyclerView
            adapter = MainAdapter(arrayListOf(),this@HomeFragment)
        recyclerView!!.layoutManager = LinearLayoutManager(requireContext())
        recyclerView!!.addItemDecoration(
                DividerItemDecoration(
                    recyclerView!!.context,
                    (recyclerView!!.layoutManager as LinearLayoutManager).orientation
                )
            )
        recyclerView!!.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getUsers().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView!!.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        recyclerView!!.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView!!.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(users: List<User>) {
        adapter.apply {
            this!!.addUsers(users)
        }
    }

    override fun onItemClick(user: User) {
        viewModel.setUserData(user)
        this.findNavController().navigate(R.id.detailsFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView!!.adapter = null
        adapter = null
        recyclerView = null
    }

}