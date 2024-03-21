package ama.test.block1

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataAkcii(
    val id: Int,
    @SerializedName("date_start")
    val dateStart: Long,
    @SerializedName("url_image")
    val urlImage: String,
    val name: String,
    val description: String
): Parcelable