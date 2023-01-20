package com.assignment.flickerfinder.di

import android.app.Application
import android.util.Log
import com.assignment.flickerfinder.BuildConfig
import com.assignment.flickerfinder.constants.Constants
import com.assignment.flickerfinder.network.api.FlickerApi
import com.assignment.flickerfinder.utils.FlickerConnectivityChecker
import com.google.gson.GsonBuilder
import com.moczul.ok2curl.CurlInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
open class NetworkModule {
        @Provides
        @Singleton
        open fun provideOkHttpClient(context: Application): OkHttpClient {

            return OkHttpClient.Builder()
                .readTimeout(Constants.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(Constants.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(NoConnectivityInterceptor())
                .addInterceptor(AuthorizationHeaderInterceptor())
                .addInterceptor(CurlInterceptor { message ->
                    Log.d(
                        "okhttp.OkHttpClient",
                        "log: $message "
                    )
                })
                .build()
        }

        @Provides
        @Singleton
        open fun provideFlickerApi(client: OkHttpClient): FlickerApi {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            return  Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
                .create(FlickerApi::class.java)
        }
    }

    class NoConnectivityInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            if (!FlickerConnectivityChecker.instance.isOnline()) {
                throw IOException("No internet connection")
            } else {
                return chain.proceed(chain.request())
            }
        }
    }

    class AuthorizationHeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            val apiKey = BuildConfig.FLICKER_API_KEY

            if (apiKey.isNotEmpty()) {
                request = request.newBuilder()
                    .addHeader("api-key", apiKey)
                    .build()
            }

            return chain.proceed(request)
        }
    }

