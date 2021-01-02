package org.viper75.tvshows.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import org.viper75.tvshows.R
import org.viper75.tvshows.data.TvShow
import org.viper75.tvshows.databinding.TvShowItemBinding

class TvShowAdapter(private val listener: OnItemClickListener) : PagingDataAdapter<TvShow, TvShowAdapter.TvShowItemViewHolder>(
    TV_SHOW_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowItemViewHolder {
        val binding = TvShowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvShowItemViewHolder, position: Int) {
        val tvShow = getItem(position)
        tvShow?.let {
            holder.bind(it)
        }
    }

    inner class TvShowItemViewHolder(
        private val binding: TvShowItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val currentItemPos = bindingAdapterPosition
                if (currentItemPos != RecyclerView.NO_POSITION) {
                    getItem(currentItemPos)?.let {
                        listener.onItemClick(it)
                    }
                }
            }
        }

        fun bind(tvShow: TvShow) {
            binding.apply {
                Glide.with(itemView)
                    .load(tvShow.image)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(posterImageView)

                nameTextView.text = tvShow.name
                networkTextView.text = tvShow.network
                startDateTextView.text = "Started on ${tvShow.startDate}"
                statusTextView.text = tvShow.status
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(tvShow: TvShow)
    }

    companion object {
        val TV_SHOW_COMPARATOR = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
                oldItem == newItem

        }
    }
}