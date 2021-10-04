package com.example.task5.fragments.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.task5.data.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val repository: CatRepository) : ViewModel() {

     val photos = repository.getResults().cachedIn(viewModelScope)
}
