package com.intecanar.ondiet.ui.input.weight

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.intecanar.ondiet.data.database.OnDietDataBase
import com.intecanar.ondiet.data.entity.Weight
import com.intecanar.ondiet.domain.OnDietRepository
import kotlinx.coroutines.launch
import java.time.OffsetDateTime

class WeightInputViewModel (application: Application) :  AndroidViewModel(application) {
    // The ViewModel maintains a reference to the repository to get data.
    private val repository: OnDietRepository

    private val _dateTimeText = MutableLiveData<OffsetDateTime>().apply {
        value = OffsetDateTime.now()
    }

    val dateTimeText: LiveData<OffsetDateTime> = _dateTimeText

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val wordsDao = OnDietDataBase.getDatabase(application).weightDAO()
        repository = OnDietRepository(wordsDao)
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on
     * the main thread, blocking the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called
     * viewModelScope which we can use here.
     */
    fun insert(weight: Float) = viewModelScope.launch {
        repository.insert(Weight(weight = weight, date = dateTimeText.value!! ))
    }

    fun setNewDateTime(offsetDateTime: OffsetDateTime?) {
        offsetDateTime?.let {
            _dateTimeText.value = it

        }
    }
}
