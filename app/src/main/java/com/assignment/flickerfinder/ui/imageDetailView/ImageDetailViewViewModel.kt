package com.assignment.flickerfinder.ui.imageDetailView

import androidx.lifecycle.ViewModel
import com.assignment.flickerfinder.data.FlickerSearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageDetailViewViewModel @Inject constructor(private val flickerSearchRepository: FlickerSearchRepository) : ViewModel() {
}
