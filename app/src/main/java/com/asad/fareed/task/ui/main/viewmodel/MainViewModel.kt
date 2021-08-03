package com.asad.fareed.task.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.asad.fareed.task.data.model.User
import com.asad.fareed.task.data.repository.MainRepository
import com.asad.fareed.task.utils.Resource
import kotlinx.coroutines.Dispatchers


class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    private var user: MutableLiveData<User> = MutableLiveData<User>()

    private var users:LiveData<Resource<List<User>>> = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getUsers()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getUsers(): LiveData<Resource<List<User>>> {
        return users
    }

    fun setUserData(data: User) {
        user.value = data
    }

    fun getUserData(): MutableLiveData<User> {
        return user
    }

}