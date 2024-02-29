package es.architectcoders.spaceexplorer.ui.favourite

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.architectcoders.domain.Apod
import es.architectcoders.domain.Photo
import es.architectcoders.spaceexplorer.R
import es.architectcoders.spaceexplorer.databinding.FavoriteItemBinding
import es.architectcoders.spaceexplorer.ui.common.inflate
import es.architectcoders.spaceexplorer.ui.common.toggleVisibilityWithAnimation

class FavouriteAdapter :
    ListAdapter<Any, FavouriteAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.favorite_item, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        when (item) {
            is Photo -> holder.bindPhoto(item)
            is Apod -> holder.bindApod(item)
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = FavoriteItemBinding.bind(view)

        fun bindPhoto(photo: Photo) {
            binding.item = photo
            binding.ibExpand.setOnClickListener {
                binding.llData.toggleVisibilityWithAnimation(binding.ibExpand)
            }
        }

        fun bindApod(apod: Apod) {
            binding.item = apod
            binding.ibExpand.setOnClickListener {
                binding.llData.toggleVisibilityWithAnimation(binding.ibExpand)
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            // Compara si los elementos tienen el mismo tipo
            if (oldItem.javaClass != newItem.javaClass) return false

            // Compara los elementos dependiendo de su tipo
            return when (oldItem) {
                is Photo -> oldItem.id == (newItem as Photo).id
                is Apod -> oldItem.id == (newItem as Apod).id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            // Implementa la lógica para comparar si el contenido de los elementos es el mismo
            return when {
                oldItem is Photo && newItem is Photo -> {
                    // Comparar los campos relevantes de los elementos Photo
                    oldItem.id == newItem.id &&
                            oldItem.imgSrc == newItem.imgSrc
                }
                oldItem is Apod && newItem is Apod -> {
                    // Comparar los campos relevantes de los elementos Apod
                    oldItem.id == newItem.id &&
                            oldItem.title == newItem.title
                    // Agrega cualquier otro campo relevante que desees comparar
                }
                else -> false
            }
        }
    }
}