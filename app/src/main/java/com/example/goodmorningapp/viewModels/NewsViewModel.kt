package com.example.goodmorningapp.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.goodmorningapp.data.models.news.NewsModel
import com.example.goodmorningapp.data.models.news.NewsSourceModel
import com.example.goodmorningapp.data.models.news.NewsStateModel
import com.example.goodmorningapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    application: Application,
    private val newsRepository: NewsRepository
) : AndroidViewModel(application) {

    private var newsStateData = NewsStateModel()
        set(value) {
            field = value
            _newsStateLiveData.value = value
        }

    private val _newsStateLiveData = MutableLiveData(newsStateData)

    val newsStateLiveData
        get() = _newsStateLiveData


    val newsSourcesData = newsRepository.newsDataFlow.asLiveData(Dispatchers.Default)
    var usedSourcesIds = newsSourcesData.value?.filter { it.isUsed }?.map { it.id }?.toMutableList()


    init {
        getNewsSources()
    }


    fun getNews(){
        val listNews = mutableListOf<NewsModel>()
        newsStateData = newsStateData.copy(isLoading = true)
        viewModelScope.launch {
            if (usedSourcesIds!= null){
                for (source in usedSourcesIds!!) {
                    listNews.addAll(newsRepository.getNews(source))
                }
            }
            newsStateData = newsStateData.copy(
                listNewsModel = listNews ,
                isLoading = false
            )
        }

    }

    private fun getNewsSources() {
        viewModelScope.launch {
            newsRepository.getNewsSources()
       }

    }


    fun check(source: NewsSourceModel) {
        if (usedSourcesIds?.contains(source.id) == true) {
            usedSourcesIds!!.remove(source.id)
        }else {
           usedSourcesIds?.add(source.id)
        }
        viewModelScope.launch{
            newsRepository.check(source.id)
        }
    }
}