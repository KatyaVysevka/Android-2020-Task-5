package com.example.task5.fragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.task5.data.CatPhoto
import com.example.task5.data.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
    val photos = repository.getResults().cachedIn(viewModelScope)

}