package mastering.andriod.heroes.models

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: CharacterData
)

data class CharacterData(
    @SerializedName("results")
    val results: List<HeroResponse>
)

