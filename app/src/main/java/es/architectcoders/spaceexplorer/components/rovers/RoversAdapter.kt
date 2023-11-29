package es.architectcoders.spaceexplorer.components.rovers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import es.architectcoders.spaceexplorer.R
import es.architectcoders.spaceexplorer.common.loadUrl
import es.architectcoders.spaceexplorer.databinding.RoversItemBinding

class RoversAdapter(private val listRovers: List<RoversObject>) :
    RecyclerView.Adapter<RoversAdapter.ViewHolder>() {

    private lateinit var binding: RoversItemBinding
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        binding = RoversItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listRovers[position]
        val llData: LinearLayout = holder.view.llData
        val ibExpand: ImageButton = holder.view.ibExpand

        holder.view.ivRoverPhoto.loadUrl(item.photo)
        holder.view.tvSol.text = item.sol.toString()
        holder.view.tvCamara.text = item.camera
        holder.view.tvEarthDate.text = item.earth_date

        ibExpand.setOnClickListener {
            if (llData.visibility == View.VISIBLE) {
                llData.animate()
                    .alpha(0f)
                    .setDuration(200)
                    .withEndAction {
                        llData.visibility = View.GONE
                        llData.alpha = 1f
                        ibExpand.setImageResource(R.drawable.ic_expand_more)
                    }
                    .start()
            } else {
                llData.visibility = View.VISIBLE
                llData.alpha = 0f
                llData.animate()
                    .alpha(1f)
                    .setDuration(200)
                    .start()
                ibExpand.setImageResource(R.drawable.ic_expand_less)
            }
        }
    }

    override fun getItemCount(): Int = listRovers.size

    class ViewHolder(var view: RoversItemBinding) : RecyclerView.ViewHolder(view.root)

}