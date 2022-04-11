package ru.inc.decideplusminus.frameworks.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.data.models.dto.SimpleEntity

@Dao
interface SolutionDao {

    @Insert(entity = SimpleEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insert(simpleEntity: SimpleEntity): Completable

    @Query("SELECT * FROM simpleentity")
    fun getAll(): Single<List<SimpleEntity>>
}