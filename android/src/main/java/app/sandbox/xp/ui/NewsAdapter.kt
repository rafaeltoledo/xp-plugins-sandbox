package app.sandbox.xp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.sandbox.xp.api.News
import app.sandbox.xp.databinding.ItemNewsBinding

class NewsAdapter(private val news: List<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemNewsBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        news[position].apply {
            holder.item.date.text = date
            holder.item.title.text = title
        }
    }

    override fun getItemCount() = news.size

    class ViewHolder(val item: ItemNewsBinding) : RecyclerView.ViewHolder(item.root)
}