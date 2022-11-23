package com.example.app

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app.adapter.ItemOneDay

class MainViewModel : ViewModel() {

    val lifeData = MutableLiveData<ItemOneDay>() //занесення даних(Верхнє значення)
    val lifeDataList = MutableLiveData<List<ItemOneDay>>(
    ) //для списка прогнозу погоди на наступні дні
}