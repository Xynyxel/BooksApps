package com.bangkit.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.core.R
import com.bangkit.core.databinding.ItemFavoriteBookBinding
import com.bangkit.core.domain.model.Book
import com.bumptech.glide.request.RequestOptions
import com.squareup.picasso.Picasso
import java.util.*

class BooksFavoriteAdapter : RecyclerView.Adapter<BooksFavoriteAdapter.ListViewHolder>() {
    private var listData = ArrayList<Book>()
    var onItemClick: ((Book) -> Unit)? = null

    companion object {
        const val BASE_URL_IMAGE = "http://covers.openlibrary.org/b/id/"
    }


    fun setData(newListData: List<Book>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_book, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemFavoriteBookBinding.bind(itemView)
        fun bind(data: Book) {
            with(binding) {
                Picasso
                    .get()
                    .load(BASE_URL_IMAGE + data.cover.toString() + "-L.jpg")
                    .apply {
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    }
                    .into(ivItemImage)
                tvItemTitle.text = data.title
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}