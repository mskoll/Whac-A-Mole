package com.mskoll.smart.whac_a_mole.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class CurrentDataViewModel : ViewModel() {

    val currScore: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val currTime: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val currState: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
}