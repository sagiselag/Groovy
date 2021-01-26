package sagi.sela.groovy.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import sagi.sela.groovy.R
import sagi.sela.groovy.databinding.FragmentPlaylistDetailBinding
import sagi.sela.groovy.model.details.PlaylistDetails
import sagi.sela.groovy.viewmodel.details.PlaylistDetailsViewModel
import sagi.sela.groovy.viewmodel.details.PlaylistDetailsViewModelFactory
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistDetailsFragment : Fragment() {

    val args: PlaylistDetailsFragmentArgs by navArgs()

    private var _binding: FragmentPlaylistDetailBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var viewModel: PlaylistDetailsViewModel

    @Inject
    lateinit var viewModelFactory: PlaylistDetailsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentPlaylistDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        val id = args.playlistId

        setupViewModel()
        viewModel.getPlaylistDetails(id)
        observeDetailsLoader()
        observePlaylistDetails()

        return view
    }

    private fun observeDetailsLoader() {
        viewModel.loader.observe(this as LifecycleOwner, { loading ->
            when (loading) {
                true -> binding.detailsLoader.visibility = View.VISIBLE
                else -> binding.detailsLoader.visibility = View.GONE
            }
        })
    }

    private fun observePlaylistDetails() {
        viewModel.playlistDetails.observe(this as LifecycleOwner) { playlistDetails ->
            if (playlistDetails.getOrNull() != null) {
                setupUI(playlistDetails)
            }
            else {
                Snackbar.make(binding.playlistsDetailsRoot,
                R.string.generic_error,
                Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setupUI(playlistDetails: Result<PlaylistDetails>) {
        binding.playlistName.text = playlistDetails.getOrNull()!!.name
        binding.playlistDetails.text = playlistDetails.getOrNull()!!.details
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(PlaylistDetailsViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlaylistDetailsFragment().apply {}
    }
}