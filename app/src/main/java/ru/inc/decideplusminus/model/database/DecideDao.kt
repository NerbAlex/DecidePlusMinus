package ru.inc.decideplusminus.model.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import io.reactivex.rxjava3.core.Completable
import ru.inc.decideplusminus.model.entity.DecideEntity

@Dao
interface DecideDao {

    @Insert(entity = DecideEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insert(decideEntity: DecideEntity): Completable
}