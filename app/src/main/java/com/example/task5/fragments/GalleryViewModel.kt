package com.example.task5.fragments

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.task5.data.CatPhoto
import com.example.task5.data.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Flow
import javax.inject.Inject


@HiltViewModel
class GalleryViewModel @Inject constructor(private val repository: CatRepository) : ViewModel() {
//    private lateinit var allCats: LiveData<PagingData<CatPhoto>>
//
//    fun getCats(){
//        viewModelScope.launch (Dispatchers.IO){
//            allCats = repository.getResults()
//        }
//    }
    private val currentQuery = MutableLiveData<CatPhoto>()
    val photos = currentQuery.switchMap {

        repository.getResults().cachedIn(viewModelScope)
    }



}