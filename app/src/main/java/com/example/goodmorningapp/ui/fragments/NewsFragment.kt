package com.example.goodmorningapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.goodmorningapp.databinding.FragmentNewsBinding
import com.example.goodmorningapp.ui.adapters.recyclerAdapters.newsAdapter.NewsRecyclerAdapter
import com.example.goodmorningapp.viewModels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var adapter: NewsRecyclerAdapter
    private val newsViewModel: NewsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        adapter = NewsRecyclerAdapter()
        binding.recyclerNews.adapter = adapter

        newsViewModel.getNews()


        newsViewModel.liveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }







        return binding.root
    }
}