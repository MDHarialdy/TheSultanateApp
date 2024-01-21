package com.mdh.thesultanate.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdh.thesultanate.data.model.Sultanate
import com.mdh.thesultanate.data.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: AppRepository
) : ViewModel() {

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    private val _groupedSultanates = MutableStateFlow(
        repository.getAllList()
            .sortedBy { it.name }
    )

    val groupedSultanates: StateFlow<List<Sultanate>> get() = _groupedSultanates

    fun search(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            _groupedSultanates.value = repository.searchSultanate(_query.value)
                .sortedBy { it.name }
        }
    }
}
