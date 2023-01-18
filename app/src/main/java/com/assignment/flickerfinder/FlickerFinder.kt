package com.assignment.flickerfinder

import android.app.Application
import android.content.res.Resources
import com.assignment.flickerfinder.utils.FlickerConnectivityChecker
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FlickerFinder : Application() {
    companion object {
        lateinit var res: Resources
    }

    override fun onCreate() {
        super.onCreate()

        res = resources

        //connectivity checker initialization on the app start
        FlickerConnectivityChecker.instance.initializeWithApplicationContext(this)
    }
}
