package com.example.GeoRoom

import android.app.Application
import com.example.GeoRoom.utils.Graph

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}