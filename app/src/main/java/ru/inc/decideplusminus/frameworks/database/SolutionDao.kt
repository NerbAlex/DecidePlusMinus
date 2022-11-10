package ru.inc.decideplusminus.frameworks.database

import android.os.FileObserver.DELETE
import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import ru.inc.decideplusminus.data.models.entities.DetailsEntity
import ru.inc.decideplusminus.data.models.entities.SimpleEntity
import ru.inc.decideplusminus.presentation.ui.simple.SimpleVO

@Dao
interface SolutionDao {

    @Insert(entity = SimpleEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun insert(simpleEntity: SimpleEntity): Completable

//    @Delete(entity = SimpleEntity::class)
//    fun delete(simpleEntity: SimpleEntity): Completable

    @Query("DELETE FROM simpleentity WHERE id = :id")
    fun deleteSimple(id: Long): Completable

    @Query("DELETE FROM detailsentity WHERE parentId = :parentId")
    fun deleteDetails(parentId: Long): Completable

    @Query("DELETE FROM detailsentity WHERE id = :id")
    fun deleteDetail(id: Long): Completable

//    @Transaction
//    fun delete(id: Long): Completable { // todo на сколько это надежно, сохраняется ли последовательность вызова методов
//        deleteSimple(id)
//        deleteDetails(id)
//        return Completable.complete()
//    }

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