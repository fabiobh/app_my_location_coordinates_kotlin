package com.uaialternativa.mycoordinateslocation.repository

import androidx.lifecycle.LiveData
import com.uaialternativa.mycoordinateslocation.data.UserDao
import com.uaialternativa.mycoordinateslocation.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }

}