package com.asad.fareed.task.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.asad.fareed.task.R
import com.asad.fareed.task.data.api.ApiHelper
import com.asad.fareed.task.data.api.RetrofitBuilder
import com.bumptech.glide.Glide
import com.asad.fareed.task.data.model.User
import com.asad.fareed.task.ui.base.ViewModelFactory
import com.asad.fareed.task.ui.main.adapter.MainAdapter.DataViewHolder
import com.asad.fareed.task.ui.main.callbacks.ItemClickHandler
import com.asad.fareed.task.ui.main.view.fragments.HomeFragment
import com.asad.fareed.task.ui.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.item_layout.view.imageViewAvatar
import kotlinx.android.synthetic.main.item_layout.view.textViewUserEmail
import kotlinx.android.synthetic.main.item_layout.view.textViewUserName

class MainAdapter(private val users: ArrayList<User>, context: HomeFragment) : RecyclerView.Adapter<DataViewHolder>() {

    private val mContext = context

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: User) {
            itemView.apply {
                textViewUserName.text = user.name
                textViewUserEmail.text = user.email
                Glide.with(imageViewAvatar.context)
                    .load(user.avatar)
                    .into(imageViewAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
        holder.itemView.setOnClickListener {
            val handler = mContext as ItemClickHandler
            handler.onItemClick(users[position])
        }
    }

    fun addUsers(users: List<User>) {
        this.users.apply {
            clear()
            addAll(users)
            notifyItemRangeChanged(0,users.size)
        }

    }

}