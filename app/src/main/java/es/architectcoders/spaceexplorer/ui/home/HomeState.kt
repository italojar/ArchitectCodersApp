package es.architectcoders.spaceexplorer.ui.home

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import es.architectcoders.spaceexplorer.R
import es.architectcoders.spaceexplorer.common.loadUrl
import es.architectcoders.spaceexplorer.databinding.FragmentHomeBinding
import es.architectcoders.spaceexplorer.model.ApodObject


fun Fragment.buildHomeState(
    binding: FragmentHomeBinding,
    navController: NavController = findNavController(),
) = HomeState(fragment = this, binding = binding, navController = navController)

class HomeState(
    private val fragment: Fragment,
    private val binding: FragmentHomeBinding,
    private val navController: NavController
) {
    fun navigateToDetail(apodArgs: ApodObject, afterNavigate: () -> Unit) {
        val navAction = HomeFragmentDirections.actionHomeFragmentToHomeDetailFragment(apodArgs)
        navController.navigate(navAction)
        afterNavigate()
    }

    fun onBackPressed() { fragment.requireActivity().finish() }

    fun updateUi(apod: ApodObject) {
        binding.cvRover.visibility = View.VISIBLE
        binding.ivRoverPhoto.loadUrl(apod.url)
        binding.tvTitle.text = apod.title
        binding.tvDate.text = apod.date
        binding.tvExplanation.text = apod.explanation
        if (apod.copyright.isNotEmpty()) {
            binding.tvCopyright.text = fragment.getString(R.string.copyright, apod.copyright)
        } else {
            binding.tvCopyright.visibility = View.GONE
        }
    }

}