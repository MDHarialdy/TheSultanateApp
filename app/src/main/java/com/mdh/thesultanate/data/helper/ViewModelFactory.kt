package com.mdh.thesultanate.data.helper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mdh.thesultanate.data.repository.AppRepository
import com.mdh.thesultanate.ui.screen.home.HomeViewModel

class ViewModelFactory (private val repository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}