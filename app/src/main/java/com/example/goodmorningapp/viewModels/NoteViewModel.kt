package com.example.goodmorningapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.goodmorningapp.data.models.NoteModel
import com.example.goodmorningapp.repository.NoteRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    application: Application,
    private val noteRepository: NoteRepositoryImpl
) : AndroidViewModel(application) {

    private var data = listOf<NoteModel>()
        set(value) {
            field = value
            _noteLiveData.value = value
        }
    private val _noteLiveData = MutableLiveData(data)
    val noteLiveData
        get() = _noteLiveData


    fun getAll() {
        viewModelScope.launch {
            data = noteRepository.getAll().map { NoteModel.fromEntity(it) }
        }
    }

}