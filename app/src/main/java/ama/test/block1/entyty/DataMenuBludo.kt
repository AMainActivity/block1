package ama.test.block1.entyty

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataMenuBludo(
    val id: Int,
    @SerializedName("category_id")
    val categoryId: Int,
    @SerializedName("url_image")
    val urlImage: String,
    val name: String,
    val description: String,
    val cena: String,
    val calory: String,
    val massa: String,
    val sostav: String
) : Parcelable