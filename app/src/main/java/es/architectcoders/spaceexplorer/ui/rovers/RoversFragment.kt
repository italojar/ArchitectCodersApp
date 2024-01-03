package es.architectcoders.spaceexplorer.ui.rovers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import es.architectcoders.spaceexplorer.databinding.FragmentRoversBinding
import es.architectcoders.spaceexplorer.model.RoversObject

@AndroidEntryPoint
class RoversFragment : Fragment() {

    companion object {
        fun newInstance() = RoversFragment()
    }

    private lateinit var viewModel: RoversViewModel
    private lateinit var binding: FragmentRoversBinding
    private var list: List<RoversObject> = (
        listOf(
            RoversObject("https://d7lju56vlbdri.cloudfront.net/var/ezwebin_site/storage/images/_aliases/img_1col/noticias/la-nasa-confirma-la-muerte-del-rover-opportunity-en-marte/6439856-1-esl-MX/La-NASA-confirma-la-muerte-del-rover-Opportunity-en-Marte.jpg",
            1000,
            "Camara de navegacion",
            "2022-01-01"),
            RoversObject("https://upload.wikimedia.org/wikipedia/commons/d/dc/PIA16239_High-Resolution_Self-Portrait_by_Curiosity_Rover_Arm_Camera.jpg",
                1001,
                "Camara de navegacion",
                "2022-09-03"),
            RoversObject("https://www.nationalgeographic.com.es/medio/2022/04/05/selfie-del-rover-de-la-nasa-perseverance_57a72028_1280x1215.jpg",
                1002,
                "Camara de navegacion",
                "2022-10-06"),
            RoversObject("https://static.nationalgeographic.es/files/styles/image_3200/public/01marscuriosityturns3.ngsversion.1470424028586.jpg?w=1900&h=1267",
                1003,
                "Camara de navegacion",
                "2022-12-07")
        )
            )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoversBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RoversViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvRovers.adapter = RoversAdapter(list)
    }

}