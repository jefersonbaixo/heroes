import mastering.andriod.heroes.models.CharacterResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {
    @GET("characters")
    fun getCharacters(
        @Query("offset") offset: Int
    ): Call<CharacterResponse>
}