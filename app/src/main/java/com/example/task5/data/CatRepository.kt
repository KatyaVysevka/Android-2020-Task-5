package com.example.task5.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.task5.api.CatApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatRepository @Inject constructor (private val catApi: CatApi) {
    fun getResults() =
        Pager(
            config = PagingConfig(
                initialLoadSize = 20,
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CatPagingSource(catApi) }
        ).liveData
}
