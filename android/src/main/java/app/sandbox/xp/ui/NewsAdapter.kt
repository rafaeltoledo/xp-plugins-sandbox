package app.sandbox.xp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.sandbox.xp.AppExperiments
import app.sandbox.xp.Experiments
import app.sandbox.xp.R
import app.sandbox.xp.api.News
import app.sandbox.xp.databinding.ItemNewsBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val news = mutableListOf<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = if (Experiments.isTreated(AppExperiments.NEWS_LAYOUT)) {
            ItemNewsBinding.bind(inflater.inflate(R.layout.item_news_alternative, parent, false))
        } else ItemNewsBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        news[position].apply {
            holder.item.date.text = date
            holder.item.title.text = title
        }
    }

    override fun getItemCount() = news.size

    fun update(news: List<News>) {
        this.news.clear()
        this.news.addAll(news)
        notifyDataSetChanged()
    }

    class ViewHolder(val item: ItemNewsBinding) : RecyclerView.ViewHolder(item.root)
}