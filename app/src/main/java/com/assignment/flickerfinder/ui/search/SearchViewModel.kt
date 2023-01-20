package com.assignment.flickerfinder.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.flickerfinder.data.FlickerSearchRepository
import com.assignment.flickerfinder.network.responses.PhotoList
import com.assignment.flickerfinder.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val flickerSearchRepository: FlickerSearchRepository) : ViewModel() {
    fun performImageSearch( searchPhrase: String): MutableLiveData<Resource<PhotoList?>> {
        val liveData = MutableLiveData<Resource<PhotoList?>>()

        liveData.postValue(Resource.Loading)

        val handler = CoroutineExceptionHandler { _, exception ->
            liveData.postValue(Resource.Error(exception))
        }

        viewModelScope.launch(handler) {
            flickerSearchRepository.searchFlickerImages(searchPhrase).let { result ->
                when (result) {
                    is Resource.Success -> {
                        liveData.postValue(Resource.Success(result.data))
                    }
                    is Resource.FormattedError -> {
                        liveData.postValue(Resource.FormattedError(result.data))
                    }
                    else -> {
                        liveData.postValue(Resource.Error(Exception()))
                    }
                }
            }
        }
        return liveData
    }
}