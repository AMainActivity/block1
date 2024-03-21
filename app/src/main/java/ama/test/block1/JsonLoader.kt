package ama.test.block1

import android.app.Application
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.util.Arrays




class JsonLoader(
    private val application: Application
) {
    fun getJsonDataFromAsset(fileName: String): String? {
        val jsonString: String
        try {
            jsonString = application.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    inline fun <reified T> loadJobsFromAssets(fileName: String): List<T> {
        try {
            val jsonFileString = getJsonDataFromAsset(fileName)
            val gson = Gson()
            val listDTOType = object : TypeToken<List<T>>() {}.type
            return gson.fromJson(jsonFileString, listDTOType)

        } catch (e: Exception) {
            throw Exception("не удалось загрузить")
        }
    }
}