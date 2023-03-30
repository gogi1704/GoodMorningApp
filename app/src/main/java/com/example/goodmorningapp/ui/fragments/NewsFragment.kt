package com.example.goodmorningapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.goodmorningapp.data.models.news.NewsSourceModel
import com.example.goodmorningapp.databinding.FragmentNewsBinding
import com.example.goodmorningapp.ui.adapters.recyclerAdapters.newsAdapter.NewsRecyclerAdapter
import com.example.goodmorningapp.ui.adapters.recyclerAdapters.newsSourcesAdapter.NewsSourcesClickListener
import com.example.goodmorningapp.ui.adapters.recyclerAdapters.newsSourcesAdapter.NewsSourcesRecyclerAdapter
import com.example.goodmorningapp.viewModels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsRecyclerAdapter
    private lateinit var newsSourcesAdapter: NewsSourcesRecyclerAdapter
    private val newsViewModel: NewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        newsAdapter = NewsRecyclerAdapter()
        newsSourcesAdapter = NewsSourcesRecyclerAdapter(object : NewsSourcesClickListener {
            override fun clickCheckBox(source:NewsSourceModel) {
               newsViewModel.check(source)
            }

        })
        binding.recyclerNews.adapter = newsAdapter
        binding.recyclerNewsSources.adapter = newsSourcesAdapter


        newsViewModel.newsStateLiveData.observe(viewLifecycleOwner) {
            newsAdapter.submitList(it.listNewsModel)

        }

        newsViewModel.newsSourcesData.observe(viewLifecycleOwner){ list ->
            newsSourcesAdapter.submitList(list)
            newsViewModel.usedSourcesIds = list?.filter { it.isUsed }?.map { it.id }?.toMutableList()
            newsViewModel.getNews()
        }



        return binding.root
    }


}