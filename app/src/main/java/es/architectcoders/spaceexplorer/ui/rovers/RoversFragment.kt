package es.architectcoders.spaceexplorer.ui.rovers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import es.architectcoders.domain.Error
import es.architectcoders.spaceexplorer.R
import es.architectcoders.spaceexplorer.databinding.FragmentRoversBinding
import es.architectcoders.spaceexplorer.ui.common.launchAndCollectT

@AndroidEntryPoint
class RoversFragment : Fragment(R.layout.fragment_rovers) {

    companion object {
        fun newInstance() = RoversFragment()
    }

    private val viewModel: RoversViewModel by viewModels()

    private val roversAdapter : RoversAdapter = RoversAdapter {
        viewModel.saveRoversAsFavourite(it)
    }

//    private var list: List<PhotoObject> = (
//        listOf(
//            PhotoObject("https://d7lju56vlbdri.cloudfront.net/var/ezwebin_site/storage/images/_aliases/img_1col/noticias/la-nasa-confirma-la-muerte-del-rover-opportunity-en-marte/6439856-1-esl-MX/La-NASA-confirma-la-muerte-del-rover-Opportunity-en-Marte.jpg",
//            1000,
//            "Camara de navegacion",
//            "2022-01-01"),
//            PhotoObject("https://upload.wikimedia.org/wikipedia/commons/d/dc/PIA16239_High-Resolution_Self-Portrait_by_Curiosity_Rover_Arm_Camera.jpg",
//                1001,
//                "Camara de navegacion",
//                "2022-09-03"),
//            PhotoObject("https://www.nationalgeographic.com.es/medio/2022/04/05/selfie-del-rover-de-la-nasa-perseverance_57a72028_1280x1215.jpg",
//                1002,
//                "Camara de navegacion",
//                "2022-10-06"),
//            PhotoObject("https://static.nationalgeographic.es/files/styles/image_3200/public/01marscuriosityturns3.ngsversion.1470424028586.jpg?w=1900&h=1267",
//                1003,
//                "Camara de navegacion",
//                "2022-12-07")
//        )
//            )

//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
//    ): View {
//        binding = FragmentRoversBinding.inflate(inflater, container, false)
//        return binding.root
//    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(RoversViewModel::class.java)
//        // TODO: Use the ViewModel
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentRoversBinding.bind(view).apply {
            rvRovers.adapter = roversAdapter
        }

        viewLifecycleOwner.launchAndCollectT(viewModel.state) {
            binding.loading = it.loading
            binding.photoList = it.photoList
            binding.error = it.error.also { error ->
                if (error != null) {
                    when (error) {
                        is Error.Server -> showErrorDialog(getString(R.string.server_error))
                        is Error.Connectivity -> showErrorDialog(getString(R.string.connectivity_error))
                        is Error.Unknown -> showErrorDialog(getString(R.string.unknown_error))
                    }
                }
            }
        }

        viewModel.onUiReady()
    }

    private fun showErrorDialog(error: String) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.dialog_title_error))
            .setMessage(error)
            .setNegativeButton(getString(R.string.dialog_cancel)) { dialog, _ -> dialog.dismiss() }
            .setPositiveButton(getString(R.string.dialog_retry)) { _, _ -> viewModel.retry() }
            .setCancelable(false)
            .show()
    }
}