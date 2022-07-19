package com.mskoll.smart.whac_a_mole.data

class ScoreRepo(private val scoreDao: ScoreDao) {

    val getScore = scoreDao.getScore()

    val getBest = scoreDao.getBest()

    suspend fun addScore(score: Score) {
        scoreDao.addScore(score)
    }
}