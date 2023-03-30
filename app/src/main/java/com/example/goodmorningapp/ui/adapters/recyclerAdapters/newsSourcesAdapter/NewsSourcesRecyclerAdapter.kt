package com.example.goodmorningapp.ui.adapters.recyclerAdapters.newsSourcesAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.goodmorningapp.data.models.news.NewsSourceModel
import com.example.goodmorningapp.databinding.RecyclerNewsSourceItemBinding
import javax.inject.Inject

interface NewsSourcesClickListener {
    fun clickCheckBox(source: NewsSourceModel)
}

class NewsSourcesRecyclerAdapter @Inject constructor(
    private val listener: NewsSourcesClickListener,

) :
    ListAdapter<NewsSourceModel, NewsSourcesRecyclerAdapter.NewsSourcesViewHolder>(
        NewsSourcesDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsSourcesViewHolder {
        val binding = RecyclerNewsSourceItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsSourcesViewHolder(binding, listener )
    }

    override fun onBindViewHolder(holder: NewsSourcesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class NewsSourcesViewHolder @Inject constructor(
        private val binding: RecyclerNewsSourceItemBinding,
        private val listener: NewsSourcesClickListener,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewsSourceModel) {
            with(binding) {
                textNewsSourceTitle.text = item.name
                textNewsSourceUrl.text = item.url
                checkbox.isChecked = item.isUsed
                checkbox.setOnClickListener {
                    listener.clickCheckBox(item)
                }
            }
        }

    }

}