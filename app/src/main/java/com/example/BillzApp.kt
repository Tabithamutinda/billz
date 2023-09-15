package com.example

import android.app.Application
import android.content.Context


class BillzApp : Application() {
    //    use it to give us access to the global context
    companion object {
        lateinit var appContext: Context
    }
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
//        scope is from the moment the app is launched to when it is killed - add it in the manifest file
    }
}