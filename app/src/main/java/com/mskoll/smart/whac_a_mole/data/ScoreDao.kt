package com.mskoll.smart.whac_a_mole.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScoreDao {

    @Insert
    suspend fun addScore(score: Score)

    @Query("SELECT * FROM score ORDER BY score DESC LIMIT 10")
    fun getScore(): LiveData<List<Score>>

    @Query("SELECT * FROM score ORDER BY score DESC LIMIT 1")
    fun getBest(): LiveData<Score>

}