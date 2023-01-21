package com.assignment.flickerfinder.network.api

import com.assignment.flickerfinder.constants.UrlEndpoints.Companion.flickerSearch
import com.assignment.flickerfinder.network.responses.PhotoList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
interface FlickerApi {
    /**
     * Get Image list
     */
    @GET(flickerSearch)
    suspend fun searchImages(@Query("api_key") apiKey : String,
                             @Query("text")  searchPhrase : String?,
                             @Query("format") format: String,
                             @Query("per_page") perPageImageCount : Int,
                             @Query("license") licenceType : Int,
                             @Query("extras") urlImageResources : String,
                            @Query("nojsoncallback") callBackToken : Int): Response<PhotoList>
}