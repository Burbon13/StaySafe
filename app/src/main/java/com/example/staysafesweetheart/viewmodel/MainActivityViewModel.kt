package com.example.staysafesweetheart.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MainActivityViewModel : ViewModel() {
    val idOfCurrentFragment = MutableLiveData<Int>()
}
