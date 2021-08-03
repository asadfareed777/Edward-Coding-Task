package com.asad.fareed.task.ui.main.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.asad.fareed.task.data.api.ApiHelper
import com.asad.fareed.task.data.api.RetrofitBuilder
import com.asad.fareed.task.data.model.User
import com.asad.fareed.task.databinding.FragmentDetailsBinding
import com.asad.fareed.task.databinding.FragmentHomeBinding
import com.asad.fareed.task.ui.base.ViewModelFactory
import com.asad.fareed.task.ui.main.viewmodel.MainViewModel
import com.asad.fareed.task.utils.Status
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.item_layout.view.*


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater,container,false)
        setupViewModel()
        setupObservers()
        return binding.root
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            requireActivity(),
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }



    private fun setupObservers() {
        viewModel.getUserData().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                setUserData(resource)
            }
        })
    }

    private fun setUserData(user: User) {
         user.apply {
           binding.let {
               tv_username.text = name
               tv_follower_count.text = follower
               tv_following_count.text = following
               tv_collection_count.text = collection
               tv_email.text = email
               tv_phone.text = phone
               tv_webiste.text = website
               Glide.with(iv_user.context)
                   .load(avatar)
                   .into(iv_user)
           }
        }
    }

}