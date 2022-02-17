package ca.qc.cstj.s02constraintlayout.core

import androidx.lifecycle.MutableLiveData

fun String.laMethodeYannick() : String {
    return "Yannick"
}

fun <T> MutableLiveData<T>.notify() {
    this.value = this.value
}