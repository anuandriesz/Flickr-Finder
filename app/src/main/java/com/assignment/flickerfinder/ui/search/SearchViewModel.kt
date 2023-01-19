package com.assignment.flickerfinder.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {

    var sampleText = MutableLiveData<String>()

    init {
        sampleText.value = "Hello World"
    }
}