package com.example.home_feature.pages.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.base_feature.utils.layoutInflater
import com.example.base_feature.utils.loadUrl
import com.example.base_feature.utils.margin
import com.example.domain.model.movie.MovieDetailModel
import com.example.uikit.databinding.ItemHomeSecondListBinding

class HomeSecondAdapter(
    private val onClick: ((MovieDetailModel) -> Unit)? = null,
) : RecyclerView.Adapter<HomeSecondAdapter.HomeSecondAdapterViewHolder>() {

    var items = emptyList<MovieDetailModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = HomeSecondAdapterViewHolder(
        ItemHomeSecondListBinding.inflate(
            parent.context.layoutInflater,
            parent,
            false
        )
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: HomeSecondAdapterViewHolder, position: Int) {
        items[position].let { model ->
            holder.bind(model, position)
            holder.itemView.setOnClickListener {
                onClick?.invoke(model)
            }
        }
    }

    inner class HomeSecondAdapterViewHolder(private val binding: ItemHomeSecondListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: MovieDetailModel, position: Int) {
            binding.ivMovie.apply {
                loadUrl(model.posterPath)
                contentDescription = this.context.getString(
                    com.example.uikit.R.string.post_description, model.title
                )
                margin(
                    left = if (position == 0) 24F else 0F,
                    right = if (position == itemCount - 1) 24F else 16F,
                )
            }
        }
    }
}