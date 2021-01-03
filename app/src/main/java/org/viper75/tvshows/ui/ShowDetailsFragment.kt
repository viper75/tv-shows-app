package org.viper75.tvshows.ui

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import org.viper75.tvshows.R
import org.viper75.tvshows.databinding.TvShowDetailsBinding

class ShowDetailsFragment : Fragment(R.layout.tv_show_details) {

    private val args by navArgs<ShowDetailsFragmentArgs>()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = TvShowDetailsBinding.bind(view)

        binding.apply {
            val tvShow = args.tvShow

            Glide.with(this@ShowDetailsFragment)
                    .load(tvShow.image)
                    .error(R.drawable.ic_error)
                    .centerCrop()
                    .listener(object : RequestListener<Drawable>{
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            detailsProgressIndicator.isVisible = false
                            return false
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            detailsProgressIndicator.isVisible = false
                            showName.isVisible = true
                            showNetwork.isVisible = true
                            showStartDate.isVisible = true
                            showStatus.isVisible = true
                            return false
                        }
                    })
                    .into(showPosterImageView)

            showName.text = tvShow.name
            showNetwork.text = "${tvShow.network} (${tvShow.country})"
            showStartDate.text = tvShow.startDate
            showStatus.text = tvShow.status
        }
    }
}