package es.architectcoders.spaceexplorer.ui.mars

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.architectcoders.domain.NotificationsItem
import es.architectcoders.spaceexplorer.R
import es.architectcoders.spaceexplorer.databinding.NotificationItemBinding
import es.architectcoders.spaceexplorer.ui.common.basicDiffUtil
import es.architectcoders.spaceexplorer.ui.common.inflate

class NotificationsAdapter :
    ListAdapter<NotificationsItem, NotificationsAdapter.ViewHolder>(basicDiffUtil {
            old, new -> old.messageID == new.messageID }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationsAdapter.ViewHolder {
        val view = parent.inflate(R.layout.notification_item, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationsAdapter.ViewHolder, position: Int) {
        val notificationsItem = getItem(position)
        holder.bind(notificationsItem)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = NotificationItemBinding.bind(view)

        fun bind(notificationsItem: NotificationsItem) {
            binding.notificationsItem = notificationsItem
        }
    }
}