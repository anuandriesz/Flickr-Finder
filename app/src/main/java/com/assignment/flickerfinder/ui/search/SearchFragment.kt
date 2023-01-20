package com.assignment.flickerfinder.ui.search

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.flickerfinder.R
import com.assignment.flickerfinder.databinding.FragmentSearchBinding
import com.assignment.flickerfinder.ui.adapters.AdapterPhotoList
import com.assignment.flickerfinder.utils.ErrorDialog.showAlertDialog
import com.assignment.flickerfinder.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private var mAdapterPhotoList: AdapterPhotoList? = null
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding:FragmentSearchBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchBinding.bind(view)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        binding.searchView.isActivated = true
        binding.searchView.queryHint = getString(R.string.type_phrase)
        binding.searchView.onActionViewExpanded()
        binding.searchView.isIconified = false
        binding.searchView.clearFocus()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchImages(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun searchImages(query: String?) {
        if (query != null && query.isNotEmpty()) {
            binding.progressLoader.visibility = View.VISIBLE
        viewModel.performImageSearch(query)
            .observe(viewLifecycleOwner){ response ->
                binding.progressLoader.visibility = View.GONE
            when(response){
                is Resource.Success -> {
                    response.data?.photos?.let {
                        if (it.photo != null) {
                        setAdapterItems(it.photo)
                        }
                    }
                }
                is Resource.Error -> {
                    response.exception
                        context?.showAlertDialog(
                            title = getString(R.string.error_occurred),
                            message = "${getString(R.string.please_restart_the_search)}",
                            ok = Pair(getString(R.string.ok)){

                            }
                        )
                } else -> {}
            }

        }
        } else {
            AdapterPhotoList(null)

        }
    }

    private fun setAdapterItems(photos: List<com.assignment.flickerfinder.network.responses.Photo>?) {
        mAdapterPhotoList = AdapterPhotoList(photos)
        binding.photosRecyclerView.adapter = mAdapterPhotoList
        binding.photosRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        mAdapterPhotoList?.cardOnClickListener(object : AdapterPhotoList.PhotoAdapterActionListener {
            override fun photoOnClick(item: com.assignment.flickerfinder.network.responses.Photo?) {
                //navigate to photo details
            }
        })
    }

}