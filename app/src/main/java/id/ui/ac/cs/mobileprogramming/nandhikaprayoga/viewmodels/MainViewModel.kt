package id.ui.ac.cs.mobileprogramming.nandhikaprayoga.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val input = MutableLiveData<String>()

    fun setInput(value: String) {
        input.value = value
    }

    override fun onCleared() {
        super.onCleared()
    }
}