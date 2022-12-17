package gachon.third.umc.android

import android.app.Application
import gachon.third.umc.android.Profile.PreferenceUtil

class MainApplication : Application() {
    companion object {
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate() {
        super.onCreate()
        prefs = PreferenceUtil(applicationContext)
    }
}