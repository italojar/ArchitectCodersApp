package es.architectcoders.spaceexplorer.ui.mars

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import es.architectcoders.domain.Error
import es.architectcoders.spaceexplorer.R
import es.architectcoders.spaceexplorer.databinding.FragmentMarsBinding
import es.architectcoders.spaceexplorer.ui.common.launchAndCollectT

@AndroidEntryPoint
class MarsFragment : Fragment(R.layout.fragment_mars) {

    companion object {
        fun newInstance() = MarsFragment()
    }

    private val viewModel: MarsViewModel by viewModels()

    private val notificationsAdapter: NotificationsAdapter = NotificationsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMarsBinding.bind(view).apply {
            rvNotifications.adapter = notificationsAdapter
        }
        viewLifecycleOwner.launchAndCollectT(viewModel.state) {
            binding.loading = it.loading
            binding.notificationsList = it.notificationsList
            Log.d("LIST FRAGMENT///////", it.notificationsList.toString())
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