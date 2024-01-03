package es.architectcoders.spaceexplorer.ui.rovers

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import es.architectcoders.spaceexplorer.common.loadUrl
import es.architectcoders.spaceexplorer.common.toggleVisibilityWithAnimation
import es.architectcoders.spaceexplorer.databinding.RoversItemBinding
import es.architectcoders.spaceexplorer.model.RoversObject

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

        with(holder.view) {
            ivRoverPhoto.loadUrl(item.photo)
            tvSol.text = item.sol.toString()
            tvCamara.text = item.camera
            tvEarthDate.text = item.earth_date
        }

        ibExpand.setOnClickListener {
            llData.toggleVisibilityWithAnimation(ibExpand)
        }
    }

    override fun getItemCount(): Int = listRovers.size

    class ViewHolder(var view: RoversItemBinding) : RecyclerView.ViewHolder(view.root)

}