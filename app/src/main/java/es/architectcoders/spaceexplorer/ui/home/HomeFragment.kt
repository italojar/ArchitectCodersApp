package es.architectcoders.spaceexplorer.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import es.architectcoders.spaceexplorer.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

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
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    binding.updateState(state) }
            }
        }
    }

    private fun FragmentHomeBinding.updateState(uiState: HomeViewModel.UiState) {
        uiState.let { state ->
            if (state.loading) { shimmerLayout.startShimmer() } else { shimmerLayout.stopShimmer() }
            state.apod?.let { apod ->
                homeState.updateUi(apod)
                ivApodFav.setOnClickListener { viewModel.saveApodAsFavourite(apod) }
                cvRover.setOnClickListener { viewModel.onApodClicked(apod) }
                tvSeeMore.setOnClickListener { viewModel.onApodClicked(apod) }
            }
            state.navigateTo?.let { apodObject ->
                homeState.navigateToDetail(
                    apodArgs = apodObject,
                    afterNavigate = { viewModel.onApodNavigationDone() }
                )
            }
            if (state.onBackPressed) { homeState.onBackPressed() }
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