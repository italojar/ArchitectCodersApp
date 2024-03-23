package es.architectcoders.spaceexplorer.ui.rovers

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import es.architectcoders.domain.Photo

@BindingAdapter("items")
fun RecyclerView.setItems(photos: List<Photo>?) {
    if (photos != null) {
        (adapter as? RoversAdapter)?.submitList(photos)
    }
}