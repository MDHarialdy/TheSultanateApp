package com.mdh.thesultanate.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdh.thesultanate.data.model.Sultanate
import com.mdh.thesultanate.data.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: AppRepository
) : ViewModel() {

    private val _sultanates = MutableStateFlow<Sultanate?>(null)
    val sultanates: StateFlow<Sultanate?> = _sultanates

    fun getSultanateByUserId(id: Long) {
        viewModelScope.launch {
            _sultanates.value = repository.getSultanateById(id)
        }
    }
}
