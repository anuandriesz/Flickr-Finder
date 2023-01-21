package com.assignment.flickerfinder.ui.search

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.flickerfinder.R
import com.assignment.flickerfinder.constants.Constants.Companion.IMAGE_NAME
import com.assignment.flickerfinder.constants.Constants.Companion.IMAGE_URL
import com.assignment.flickerfinder.databinding.FragmentSearchBinding
import com.assignment.flickerfinder.network.responses.Photo
import com.assignment.flickerfinder.ui.adapters.AdapterPhotoList
import com.assignment.flickerfinder.utils.Dialog.showAlertDialog
import com.assignment.flickerfinder.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private var mAdapterPhotoList: AdapterPhotoList? = null
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var binding:FragmentSearchBinding

    private val navigationController: NavController by lazy {
        Navigation.findNavController(requireView())
    }

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

        //Search functionality
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                //searchImages(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchImages(newText)
                return false
            }
        })

        //This block of code will be executed when the user presses the cross button
        binding.searchView.setOnCloseListener {
            emptyPhotoAdapter()
            return@setOnCloseListener false
        }
    }

    //Search API call handling
    private fun searchImages(query: String?) {
        if ((query != null) && query.isNotEmpty()) {

        viewModel.performImageSearch(query)
            .observe(viewLifecycleOwner){ response ->
            when(response){
                is Resource.Loading -> {
                    setLoadingState(true)
                }

                is Resource.Success -> {
                    setLoadingState(false)
                    response.data?.photos?.let {
                        if (it.photo != null) {
                            setAdapterItems(it.photo)
                        }
                    }
                }
                is Resource.Error -> {
                    setLoadingState(false)
                    response.exception
                        context?.showAlertDialog(
                            title = getString(R.string.error_occurred),
                            message = getString(R.string.please_restart_the_search),
                            ok = Pair(getString(R.string.ok)){
                            }
                        )
                } else -> {
                    setLoadingState(false)
                }
            }

        }
        } else {
            emptyPhotoAdapter()
        }
    }

    private fun setLoadingState(state: Boolean){
        if(state) {
            binding.customProgressLoaderLayout.progressLoader.visibility = View.VISIBLE
            binding.customProgressLoaderLayout.txtLoadingState.visibility = View.VISIBLE
        } else {
            binding.customProgressLoaderLayout.progressLoader.visibility = View.GONE
            binding.customProgressLoaderLayout.txtLoadingState.visibility = View.GONE
        }
    }

    //Setting search results  to the recycler view
    private fun setAdapterItems(photos: List<Photo>?) {
        mAdapterPhotoList = AdapterPhotoList(this,photos)
        binding.photosRecyclerView.adapter = mAdapterPhotoList
        binding.photosRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Recycler view item on click event
        //Navigate photo item to PhotoDetails View fragment.
        mAdapterPhotoList?.cardOnClickListener(object : AdapterPhotoList.PhotoAdapterActionListener {
            override fun photoOnClick(item: com.assignment.flickerfinder.network.responses.Photo?) {
                //navigate to photo details
                val params = Bundle()
                params.putString(IMAGE_URL,item?.urlO.toString())
                params.putString(IMAGE_NAME,item?.title )
                navigationController.navigate(R.id.imageDetailViewFragment, params)

            }
        })
    }

    private fun emptyPhotoAdapter(){
        mAdapterPhotoList?.submitList(emptyList())
        mAdapterPhotoList?.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        viewModel.liveData.observe(viewLifecycleOwner) { response ->
            when(response){
                is Resource.Success -> {
                    response.data?.photos?.let {
                        if (it.photo != null) {
                            setAdapterItems(it.photo)
                        }
                    }
                }
                else -> {}
            }
        }
    }

}