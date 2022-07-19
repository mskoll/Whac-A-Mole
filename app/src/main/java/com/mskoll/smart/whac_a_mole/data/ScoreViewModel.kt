package com.mskoll.smart.whac_a_mole.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScoreViewModel(application: Application) : AndroidViewModel(application) {

    val getScore: LiveData<List<Score>>
    val getBest: LiveData<Score>
    private val repository: ScoreRepo

    init {
        val scoreDao = WAMDatabase.getDatabase(application).scoreDao()
        repository = ScoreRepo(scoreDao)
        getScore = repository.getScore
        getBest = repository.getBest
    }

    fun addScore(score: Score) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addScore(score)
        }
    }
}