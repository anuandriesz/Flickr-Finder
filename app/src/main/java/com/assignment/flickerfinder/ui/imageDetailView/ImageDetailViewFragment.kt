package com.assignment.flickerfinder.ui.imageDetailView

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.assignment.flickerfinder.constants.Constants
import com.assignment.flickerfinder.databinding.FragmentImageDetailViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailViewFragment : Fragment() {
    private lateinit var binding: FragmentImageDetailViewBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentImageDetailViewBinding.bind(view)
        binding.lifecycleOwner = this
        displayImage()

    }

    private fun displayImage() {
        val imageUrl = arguments?.getString(Constants.IMAGE_URL)
        val imageName = arguments?.getString(Constants.IMAGE_NAME)
        if(imageUrl?.isNotEmpty() == true && imageName?.isNotEmpty() == true){
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(10))
            Glide.with(this)
                .load(imageUrl)
                .apply(requestOptions)
                .skipMemoryCache(true)//for caching the image url in case phone is offline
                .into(binding.image)
        }
    }

}