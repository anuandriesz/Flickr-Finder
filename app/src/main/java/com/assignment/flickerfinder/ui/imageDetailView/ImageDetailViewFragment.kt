package com.assignment.flickerfinder.ui.imageDetailView

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assignment.flickerfinder.R

class ImageDetailViewFragment : Fragment() {

    companion object {
        fun newInstance() = ImageDetailViewFragment()
    }

    private lateinit var viewModel: ImageDetailViewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_detail_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ImageDetailViewViewModel::class.java)
        // TODO: Use the ViewModel
    }

}