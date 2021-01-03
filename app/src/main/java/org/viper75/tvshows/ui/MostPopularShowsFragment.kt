package org.viper75.tvshows.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.LoadState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.error_message_layout.view.*
import kotlinx.android.synthetic.main.tv_shows.*
import org.viper75.tvshows.R
import org.viper75.tvshows.adapters.TvShowAdapter
import org.viper75.tvshows.adapters.TvShowLoadStateAdapter
import org.viper75.tvshows.data.TvShow
import org.viper75.tvshows.databinding.TvShowsBinding
import org.viper75.tvshows.viewmodels.MostPopularShowsViewModel

@AndroidEntryPoint
class MostPopularShowsFragment : Fragment(R.layout.tv_shows), TvShowAdapter.OnItemClickListener {

    private val viewModel by viewModels<MostPopularShowsViewModel>()

    private var _binding: TvShowsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = TvShowsBinding.bind(view)

        val adapter = TvShowAdapter(this)
        binding.apply {
            tvShowsRecyclerView.setHasFixedSize(true)
            tvShowsRecyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                    header = TvShowLoadStateAdapter { adapter.retry() },
                    footer = TvShowLoadStateAdapter { adapter.retry() }
            )
        }

        viewModel.tvShows.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

        adapter.addLoadStateListener {
            binding.apply {
                progress_indicator.isVisible = it.source.refresh is LoadState.Loading
                tv_shows_recycler_view.isVisible = it.source.refresh is LoadState.NotLoading
                error_message.isVisible = it.source.refresh is LoadState.Error

                if (it.source.refresh is LoadState.NotLoading &&
                        it.append.endOfPaginationReached &&
                        adapter.itemCount < 1) {
                    tv_shows_recycler_view.isVisible = false
                    no_results.isVisible = true
                } else {
                    no_results.isVisible = false
                }

                error_message.retry_button.setOnClickListener { adapter.retry() }
            }
        }

        setHasOptionsMenu(true)
    }

    override fun onItemClick(tvShow: TvShow) {

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.popular_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i(this.javaClass.canonicalName, "Query = $query")

                query?.let {
                    binding.tvShowsRecyclerView.scrollToPosition(0)
                    viewModel.search(query)
                    searchView.clearFocus()
                }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}