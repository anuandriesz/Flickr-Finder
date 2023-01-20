package com.assignment.flickerfinder.data

import com.assignment.flickerfinder.network.responses.PhotoList
import com.assignment.flickerfinder.utils.Resource

interface FlickerSearch {
    suspend fun searchFlickerImages(searchPhrase: String): Resource<PhotoList?>
}
