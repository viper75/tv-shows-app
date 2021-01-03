package org.viper75.tvshows.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import org.viper75.tvshows.databinding.LoadStateFooterBinding

class TvShowLoadStateAdapter(
        private val retry: () -> Unit
) : LoadStateAdapter<TvShowLoadStateAdapter.TvShowLoadStateItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): TvShowLoadStateItemViewHolder {
        val binding = LoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TvShowLoadStateItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowLoadStateItemViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    inner class TvShowLoadStateItemViewHolder(
            private val binding: LoadStateFooterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.loadStateErrorMessage.retryButton.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                loadStateProgressIndicator.isVisible = loadState is LoadState.Loading
                loadStateErrorMessage.root.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}