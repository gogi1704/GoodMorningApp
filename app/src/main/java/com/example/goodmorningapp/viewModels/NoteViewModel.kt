package com.example.goodmorningapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.goodmorningapp.data.models.note.NoteModel
import com.example.goodmorningapp.data.models.weather.WeatherModel
import com.example.goodmorningapp.repository.NoteRepositoryImpl
import com.example.goodmorningapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    application: Application,
    private val noteRepository: NoteRepositoryImpl,
    private val weatherRepository: WeatherRepository
) : AndroidViewModel(application) {

    var weatherModel: WeatherModel? = null


    init {
        viewModelScope.launch {
            weatherModel = weatherRepository.getWeather()
        }
    }

    val dataFlow = noteRepository.dataFlow.asLiveData(Dispatchers.Default)

    val favouriteDataFlow = noteRepository.favouriteDataFlow.asLiveData(Dispatchers.Default)


    fun addNote(note: NoteModel) {
        viewModelScope.launch {
            noteRepository.insertNote(note.toEntity())
        }
    }


    fun favourite(note: NoteModel) {
        addNote(note.copy(isFavourite = !note.isFavourite))
    }

    fun delete(id: Int) {
        viewModelScope.launch {
            noteRepository.deleteNote(id)
        }
    }

}