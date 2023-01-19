package com.assignment.flickerfinder.ui.search


import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.assignment.flickerfinder.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {
    private val viewModel: SearchViewModel by viewModels()
   // private lateinit var binding:


}