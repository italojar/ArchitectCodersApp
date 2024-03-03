package es.architectcoders.spaceexplorer.ui.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import es.architectcoders.domain.NotificationsItem
import es.architectcoders.spaceexplorer.ui.mars.NotificationsAdapter

@BindingAdapter("url")
fun ImageView.bindUrl(url: String?) {
    if (url != null) loadUrl(url)
}

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean?) {
    visibility = if (visible == true) View.VISIBLE else View.GONE
}

@BindingAdapter("notifications")
fun RecyclerView.setItems(notifications: List<NotificationsItem>?) {
    if (notifications != null) {
        (adapter as? NotificationsAdapter)?.submitList(notifications)
    }
}