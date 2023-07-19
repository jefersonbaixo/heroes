package mastering.andriod.heroes.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "HEROES")
data class Hero(
    @PrimaryKey
    @ColumnInfo(name = "ID")
    @SerializedName("id")
    val id: Int,
    @ColumnInfo(name = "NAME")
    @SerializedName("name")
    val name: String,
    @ColumnInfo(name = "DESCRIPTION")
    @SerializedName("description")
    val description: String,
    @ColumnInfo(name = "IMAGE_URL")
    @SerializedName("imageUrl")
    val imageUrl: String
) : Parcelable