package com.example.home_feature.pages.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.base_feature.utils.layoutInflater
import com.example.domain.model.movie.MovieDetailModel
import com.example.home_feature.databinding.ItemHomeFirstListBinding

class HomeFirstAdapter(
    private val onClick: ((MovieDetailModel) -> Unit)? = null,
) : RecyclerView.Adapter<HomeFirstAdapter.HomeFirstAdapterViewHolder>() {

    var items = emptyList<HomeItemModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = HomeFirstAdapterViewHolder(
        ItemHomeFirstListBinding.inflate(
            parent.context.layoutInflater,
            parent,
            false
        )
    )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: HomeFirstAdapterViewHolder, position: Int) {
        items[position].let { model ->
            holder.bind(model, onClick)
        }
    }

    inner class HomeFirstAdapterViewHolder(private val binding: ItemHomeFirstListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: HomeItemModel, onClick: ((MovieDetailModel) -> Unit)?) {
            binding.tvTitle.text = model.gender

            val adapter = HomeSecondAdapter(onClick = onClick)
            binding.rvMovies.adapter = adapter
            adapter.items = model.movies
        }
    }
}

data class HomeItemModel(
    val gender: String,
    val movies: List<MovieDetailModel>
)
