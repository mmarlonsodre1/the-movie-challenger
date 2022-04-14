package com.example.base_feature.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.base_feature.utils.layoutInflater
import com.example.base_feature.utils.loadUrl
import com.example.base_feature.utils.margin
import com.example.domain.model.movie.MovieDetailModel
import com.example.uikit.databinding.ItemHomeSecondListBinding

class MovieDetailSimilarsAdapter(
    val items: List<MovieDetailModel>,
    val onClick: (MovieDetailModel) -> Unit
) : BaseAdapter() {

    override fun getCount() = items.size

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        val binding = ItemHomeSecondListBinding.inflate(
            parent.context.layoutInflater,
            parent,
            false
        )
        binding.ivMovie.apply {
            loadUrl(items[position].posterPath)
            margin(top = 16F, right = 0F)
            setOnClickListener {
                onClick.invoke(items[position])
            }
        }

        return binding.root
    }
}