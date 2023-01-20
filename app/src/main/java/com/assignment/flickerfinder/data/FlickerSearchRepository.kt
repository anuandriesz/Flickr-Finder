package com.assignment.flickerfinder.data

import android.util.Log
import com.assignment.flickerfinder.BuildConfig
import com.assignment.flickerfinder.constants.Constants.Companion.CALL_BACK_TOKEN
import com.assignment.flickerfinder.constants.Constants.Companion.FORMAT
import com.assignment.flickerfinder.constants.Constants.Companion.LICENCE_TYPE
import com.assignment.flickerfinder.constants.Constants.Companion.PER_PAGE_IMAGE_COUNT
import com.assignment.flickerfinder.constants.Constants.Companion.URL
import com.assignment.flickerfinder.network.api.FlickerApi
import com.assignment.flickerfinder.network.responses.PhotoList
import com.assignment.flickerfinder.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FlickerSearchRepository(private val flickerApi: FlickerApi ) : FlickerSearch {
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    override suspend fun searchFlickerImages(searchPhrase: String): Resource<PhotoList?> {
        return withContext(ioDispatcher) {
            try {
                val newTask = flickerApi.searchImages(BuildConfig.FLICKER_API_KEY,searchPhrase,
                    FORMAT, PER_PAGE_IMAGE_COUNT,
                    LICENCE_TYPE,URL, CALL_BACK_TOKEN)
                if (newTask.isSuccessful) {
                    return@withContext Resource.Success(newTask.body())
                } else {
                    return@withContext Resource.FormattedError(newTask.errorBody())
                }
            } catch (e: Exception) {
                return@withContext Resource.Error(e)
            }
        }
    }

}