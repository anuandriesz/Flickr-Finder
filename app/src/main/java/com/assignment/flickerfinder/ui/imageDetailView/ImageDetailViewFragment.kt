package com.assignment.flickerfinder.ui.imageDetailView

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.assignment.flickerfinder.R
import com.assignment.flickerfinder.constants.Constants
import com.assignment.flickerfinder.databinding.FragmentImageDetailViewBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ImageDetailViewFragment : Fragment(R.layout.fragment_image_detail_view) {
    private lateinit var binding: FragmentImageDetailViewBinding
    private val viewModel: ImageDetailViewViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()

        binding = FragmentImageDetailViewBinding.bind(view)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        displayImage()

        //Back navigation
        binding.customNavBarLayout.titleBarBack.setOnClickListener{
            Navigation.findNavController(view).popBackStack()
        }

    }

    //Display the image in a detailed view.
    private fun displayImage() {
        val imageUrl = arguments?.getString(Constants.IMAGE_URL)
        val imageName = arguments?.getString(Constants.IMAGE_NAME)
        if(imageUrl?.isNotEmpty() == true && imageName?.isNotEmpty() == true){
           //set Photo Name
            binding.customNavBarLayout.titleBarText.text = imageName

            //Set Photo
            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transforms(FitCenter(), RoundedCorners(10))
            Glide.with(this)
                .load(imageUrl)
                .apply(requestOptions)
                .skipMemoryCache(true)
                .into(binding.image)
        }
    }

}