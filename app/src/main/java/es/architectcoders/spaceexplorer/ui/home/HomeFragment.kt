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
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
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
                viewModel.state
                    .map { state -> state.loading }
                    .distinctUntilChanged()
                    .collect { loading ->
                        val shimmerLayout = binding.shimmerLayout
                        if (loading) shimmerLayout.startShimmer() else shimmerLayout.stopShimmer()
                    }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner. repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state
                    .map { state -> state.apod }
                    .distinctUntilChanged()
                    .collect { apod ->
                        apod?.let {
                            homeState.updateUi(apod)
                            binding.ivApodFav.setOnClickListener { viewModel.saveApodAsFavourite(apod) }
                            binding.cvRover.setOnClickListener { viewModel.onApodClicked(apod) }
                            binding.tvSeeMore.setOnClickListener { viewModel.onApodClicked(apod) }
                        }
                    }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state
                    .map { state -> state.navigateTo }
                    .distinctUntilChanged()
                    .collect { navArgs ->
                        navArgs?.let {
                            homeState.navigateToDetail(
                                apodArgs = navArgs,
                                afterNavigate = { viewModel.onApodNavigationDone() } )
                        }
                    }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state
                    .map { state -> state.onBackPressed }
                    .distinctUntilChanged()
                    .collect { onBackPressed ->
                        if (onBackPressed) { homeState.onBackPressed() }
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