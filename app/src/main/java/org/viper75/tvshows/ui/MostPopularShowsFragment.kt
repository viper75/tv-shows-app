package org.viper75.tvshows.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.viper75.tvshows.R
import org.viper75.tvshows.adapters.TvShowAdapter
import org.viper75.tvshows.data.TvShow
import org.viper75.tvshows.databinding.TvShowsBinding
import org.viper75.tvshows.viewmodels.MostPopularShowsViewModel

@AndroidEntryPoint
class MostPopularShowsFragment : Fragment(R.layout.tv_shows), TvShowAdapter.OnItemClickListener {

    private val viewModel by viewModels<MostPopularShowsViewModel>()

    private var binding: TvShowsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = TvShowsBinding.bind(view)

        val adapter = TvShowAdapter(this)
        binding?.apply {
            tvShowsRecyclerView.setHasFixedSize(true)
            tvShowsRecyclerView.adapter = adapter
        }

        viewModel.mostPopular()
        viewModel.tvShows.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onItemClick(tvShow: TvShow) {

    }
}