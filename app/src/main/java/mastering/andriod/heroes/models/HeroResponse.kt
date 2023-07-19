package mastering.andriod.heroes.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeroResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail
): Parcelable {
    @Parcelize
    data class Thumbnail(
        @SerializedName("path")
        val path: String,
        @SerializedName("extension")
        val extension: String
    ): Parcelable {
        fun getFullImageUrl(): String {
            return "$path.$extension"
        }
    }
}
