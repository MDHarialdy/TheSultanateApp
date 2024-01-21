package com.mdh.thesultanate.data.repository

import com.mdh.thesultanate.data.model.DummyDataSultanate
import com.mdh.thesultanate.data.model.Sultanate

class AppRepository {

    private val listSultanate = DummyDataSultanate.listSultanate

    fun getAllList(): List<Sultanate> {
        return listSultanate
    }

    fun getSultanateById(id: Long): Sultanate {
        return listSultanate.first {
            it.id == id
        }
    }

    fun searchSultanate(query: String): List<Sultanate>{
        return listSultanate.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }
}