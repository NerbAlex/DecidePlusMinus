package ru.inc.decideplusminus.frameworks.database

import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.data.models.entities.DetailsEntity
import ru.inc.decideplusminus.data.models.entities.SimpleEntity
import ru.inc.decideplusminus.frameworks.base.base_presentation.BaseSimpleItem

@Dao
interface SolutionDao {

    @Insert(entity = SimpleEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insert(simpleEntity: SimpleEntity): Completable

    @Query("SELECT * FROM simpleentity WHERE id = :id")
    fun getSimple(id: Long): Single<SimpleEntity>

    @Query("SELECT * FROM simpleentity")
    fun getAll(): Single<List<SimpleEntity>>


    @Transaction
    @Insert(entity = DetailsEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insertDetails(detailsEntity: DetailsEntity): Completable

    @Transaction
    @Query("SELECT * FROM detailsentity WHERE parentId = :parentId")
    fun getDetailsById(parentId: Long): Single<List<DetailsEntity>>
}