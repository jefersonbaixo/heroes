package mastering.andriod.heroes.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mastering.andriod.heroes.models.CharacterResponse
import mastering.andriod.heroes.models.Hero
import mastering.andriod.heroes.parseHero
import mastering.andriod.heroes.services.Client
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllHeroesViewModel : ViewModel() {

    private val _heroesData = MutableLiveData<List<Hero>>()
    val heroesData: LiveData<List<Hero>> = _heroesData

    private val _errorData = MutableLiveData<String>()
    val errorData: LiveData<String> = _errorData

    private val _loadingData = MutableLiveData<Boolean>()
    val loadingData: LiveData<Boolean> = _loadingData

    fun fetchHeroes() {
        _loadingData.value = true
        Client.marvelApiService.getCharacters(heroesData.value?.size ?: 0)
            .enqueue(object : Callback<CharacterResponse> {
                override fun onResponse(
                    call: Call<CharacterResponse>,
                    response: Response<CharacterResponse>
                ) {
                    _loadingData.value = false
                    if (response.isSuccessful) {
                        val characterResponse = response.body()
                        val characters = characterResponse?.data?.results?.map {
                            parseHero(it)
                        } ?: listOf()
                        val newList = heroesData.value?.plus(characters) ?: characters
                        _heroesData.value = newList
                    } else {
                        val error = response.errorBody()?.string()
                        _errorData.value = error ?: "Unknown error occurred"
                    }
                }

                override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                    _loadingData.value = false
                    _errorData.value = t.message ?: "Request failed"
                }
            })
    }
}