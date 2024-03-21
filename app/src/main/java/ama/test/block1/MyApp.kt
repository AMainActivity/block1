package ama.test.block1

import android.app.Application


class MyApp : Application() {
    lateinit var dataAkcii: List<DataAkcii>
    private fun loadJsonAkcii(): List<DataAkcii> {
        val jsonLoader = JsonLoader(this)
        return jsonLoader.loadJobsFromAssets("data_akcii.json")
    }

    override fun onCreate() {
        super.onCreate()
        dataAkcii = loadJsonAkcii()
    }
}