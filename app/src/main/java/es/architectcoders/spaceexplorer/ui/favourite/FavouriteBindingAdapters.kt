package es.architectcoders.spaceexplorer.ui.favourite

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("items")
fun setItems(recyclerView: RecyclerView, items: List<Any>?) {
    if (items != null) {
        // Si la lista no es nula, crea o actualiza el adaptador del RecyclerView
        val adapter = recyclerView.adapter as? FavouriteAdapter ?: FavouriteAdapter()
        recyclerView.adapter = adapter
        adapter.submitList(items)
    }
}