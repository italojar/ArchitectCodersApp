package es.architectcoders.spaceexplorer.ui.favourite

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import es.architectcoders.domain.NasaItem

@BindingAdapter("items")
fun setItems(recyclerView: RecyclerView, items: List<NasaItem>?) {
    if (items != null) {
        val adapter = recyclerView.adapter as? FavouriteAdapter ?: FavouriteAdapter()
        recyclerView.adapter = adapter
        adapter.submitList(items)
    }
}