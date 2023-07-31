package mastering.andriod.heroes.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import mastering.andriod.heroes.models.Hero

@Dao
interface HeroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(hero: Hero): Int

    @Update
    fun update(hero: Hero): Int

    @Delete
    fun delete(vararg heroes: Hero): Int

    @Query("SELECT * FROM HEROES WHERE NAME LIKE :name ORDER BY NAME ASC")
    fun getHeroesByName(name: String): LiveData<List<Hero>>
}