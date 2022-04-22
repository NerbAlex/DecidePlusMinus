package ru.inc.decideplusminus.data.models.entities

import androidx.room.Embedded
import androidx.room.Relation

data class SimpleWithDetails(
    @Embedded val simpleEntity: SimpleEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "parentId"
    )
    val detailsList: List<DetailsEntity>
)
