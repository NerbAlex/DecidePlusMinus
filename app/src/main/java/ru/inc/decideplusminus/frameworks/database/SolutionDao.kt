package ru.inc.decideplusminus.frameworks.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import io.reactivex.rxjava3.core.Completable
import ru.inc.decideplusminus.data.models.dto.SimpleSolutionEntity

@Dao
interface SolutionDao {

    @Insert(entity = SimpleSolutionEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insert(simpleSolutionEntity: SimpleSolutionEntity): Completable
}