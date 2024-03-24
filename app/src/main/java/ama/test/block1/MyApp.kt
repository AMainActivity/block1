package ama.test.block1

import ama.test.block1.UI.DataMenuBludo
import ama.test.block1.UI.DataMenuCategory
import android.app.Application


class MyApp : Application() {
    lateinit var dataAkcii: List<DataAkcii>
    lateinit var dataMenuCategory: List<DataMenuCategory>
    lateinit var dataMenuBludo: List<DataMenuBludo>
    private fun loadJsonAkcii(): List<DataAkcii> {
        val jsonLoader = JsonLoader(this)
        return jsonLoader.loadJobsFromAssets("data_akcii.json")
    }

    private fun loadMenuCategory(): List<DataMenuCategory> {
        val jsonLoader = JsonLoader(this)
        return jsonLoader.loadJobsFromAssets("data_menu_category.json")
    }
    private fun loadMenuBludo(): List<DataMenuBludo> {
        val jsonLoader = JsonLoader(this)
        return jsonLoader.loadJobsFromAssets("data_menu_bludo.json")
    }

    override fun onCreate() {
        super.onCreate()
        dataAkcii = loadJsonAkcii()
        dataMenuCategory = loadMenuCategory()
        dataMenuBludo = loadMenuBludo()
    }
}