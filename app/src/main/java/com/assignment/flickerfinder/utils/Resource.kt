package com.assignment.flickerfinder.utils

import okhttp3.ResponseBody

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Resource<out R> {

    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val exception: Any) : Resource<Nothing>()
    data class FormattedError<out T>(val data: ResponseBody?) : Resource<T>()
    object Loading : Resource<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            is FormattedError<*> -> "FormattedError[data=$data]"
            Loading -> "Loading"
        }
    }
}