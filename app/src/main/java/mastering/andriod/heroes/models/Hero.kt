package mastering.andriod.heroes.models

import com.google.gson.annotations.SerializedName

data class Hero(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail
) {
    data class Thumbnail(
        @SerializedName("path")
        val path: String,
        @SerializedName("extension")
        val extension: String
    ) {
        fun getFullImageUrl(): String {
            return "$path.$extension"
        }
    }
}