package es.architectcoders.spaceexplorer.ui.rovers

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.architectcoders.spaceexplorer.R
import es.architectcoders.spaceexplorer.databinding.RoversItemBinding
import es.architectcoders.spaceexplorer.ui.common.basicDiffUtil
import es.architectcoders.spaceexplorer.ui.common.inflate
import es.architectcoders.spaceexplorer.ui.model.PhotoObject

class RoversAdapter(private val listener: (PhotoObject) -> Unit) :
    ListAdapter<PhotoObject, RoversAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.rovers_item, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photoObject = getItem(position)
        holder.bind(photoObject)
        holder.itemView.setOnClickListener {
            listener(photoObject)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RoversItemBinding.bind(view)

        fun bind(photoObject: PhotoObject) {
            binding.photoObject = photoObject
        }
    }


}