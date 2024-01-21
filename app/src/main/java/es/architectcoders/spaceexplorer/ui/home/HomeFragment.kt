package es.architectcoders.spaceexplorer.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import es.architectcoders.spaceexplorer.R
import es.architectcoders.spaceexplorer.common.Error
import es.architectcoders.spaceexplorer.common.launchAndCollect
import es.architectcoders.spaceexplorer.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var homeState: HomeState

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeState = this.buildHomeState(binding)

        viewLifecycleOwner.launchAndCollect {
            viewModel.state
                .map { state -> state.loading }
                .distinctUntilChanged()
                .collect { loading ->
                    val shimmerLayout = binding.shimmerLayout
                    if (loading) shimmerLayout.startShimmer() else shimmerLayout.stopShimmer()
                }
        }

        viewLifecycleOwner.launchAndCollect {
            viewModel.state
                .map { state -> state.apodList }
                .distinctUntilChanged()
                .collect { apod ->
                    if (apod.isNotEmpty()) {
                        homeState.updateUi(apod.last())
                        binding.ivApodFav.setOnClickListener {
                            viewModel.saveApodAsFavourite(
                                apod.last().copy(favorite = !apod.last().favorite)
                            )
                        }
                        binding.cvApod.setOnClickListener { viewModel.onApodClicked(apod.last()) }
                        binding.tvSeeMore.setOnClickListener { viewModel.onApodClicked(apod.last()) }
                    }
                }
        }

        viewLifecycleOwner.launchAndCollect {
            viewModel.state
                .map { state -> state.navigateTo }
                .distinctUntilChanged()
                .collect { navArgs ->
                    navArgs?.let {
                        homeState.navigateToDetail(
                            apodArgs = navArgs,
                            afterNavigate = { viewModel.onApodNavigationDone() }
                        )
                    }
                }
        }

        viewLifecycleOwner.launchAndCollect {
            viewModel.state
                .map { state -> state.onBackPressed }
                .distinctUntilChanged()
                .collect { onBackPressed ->
                    if (onBackPressed) { homeState.onBackPressed() }
                }
        }

        viewLifecycleOwner.launchAndCollect {
            viewModel.state
                .map { state -> state.error }
                .collect { error ->
                    if (error != null) {
                        when (error) {
                            is Error.Server -> {
                                val a = MaterialAlertDialogBuilder(requireContext())
                                    .setTitle("An error has occurred")
                                    .setMessage(getString(R.string.server_error))
                                    .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                                    .setPositiveButton("Try again") { _, _ -> viewModel.retry() }
                                    .setCancelable(false)
                                    .show()
                            }

                            is Error.Connectivity -> {
                                MaterialAlertDialogBuilder(requireContext())
                                    .setTitle("An error has occurred")
                                    .setMessage(getString(R.string.connectivity_error))
                                    .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                                    .setPositiveButton("Try again") { _, _ -> viewModel.retry() }
                                    .setCancelable(false)
                                    .show()
                            }

                            is Error.Unknown -> {
                                MaterialAlertDialogBuilder(requireContext())
                                    .setTitle("An error has occurred")
                                    .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                                    .setMessage(getString(R.string.unknown_error))
                                    .setPositiveButton("Try again") { _, _ ->  viewModel.retry() }
                                    .setCancelable(false)
                                    .show()
                            }
                        }
                    }
                }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity()
            .onBackPressedDispatcher
            .addCallback(this@HomeFragment) { viewModel.onBackPressed() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}