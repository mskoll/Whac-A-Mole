package com.mskoll.smart.whac_a_mole.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "score")
data class Score(

    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var score: Int,
)