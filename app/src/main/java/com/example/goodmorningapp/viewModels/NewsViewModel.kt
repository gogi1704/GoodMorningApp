package com.example.goodmorningapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.goodmorningapp.data.models.news.NewsModel
import com.example.goodmorningapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    application: Application,
    private val newsRepository: NewsRepository
) : AndroidViewModel(application) {

    var data = emptyList<NewsModel>()
    set(value) {
        field = value
        liveData.value = value
    }
    val liveData = MutableLiveData(data)

    fun getNews() {
        viewModelScope.launch {
        data = newsRepository.getNewsRbc("https://newsapi.org/v2/top-headlines?sources=rbc&apiKey=484b05f2fc4f4f158f18be170b7796ae")
        }
    }
}