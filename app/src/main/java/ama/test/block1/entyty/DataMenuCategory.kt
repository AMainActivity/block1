package ama.test.block1.entyty

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataMenuCategory(
    val id: Int,
    @SerializedName("url_image")
    val urlImage: String,
    val name: String
) : Parcelable