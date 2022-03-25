package com.abinash.moviesapp.ui.main.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abinash.moviesapp.R
import com.abinash.moviesapp.databinding.FragmentMovieBinding
import com.abinash.moviesapp.di.scopes.FragmentScoped
import com.abinash.moviesapp.ui.main.BaseMainFragment
import com.abinash.moviesapp.ui.main.movielist.adapter.ImagesListAdapter
import com.abinash.moviesapp.utils.Resource

@FragmentScoped
class MovieFragment :
    BaseMainFragment<MovieViewModel, FragmentMovieBinding>(R.layout.fragment_movie){

    val imageListAdapter: ImagesListAdapter = ImagesListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val movieId = arguments?.getString("MOVIE_ID")
        movieId?.let { viewModel.getMovieImages(movieId) }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initiateViewModels()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun initViews() {
        binding.apply {
            rvMovieImages.adapter = imageListAdapter
        }
    }

    private fun initiateViewModels() {
        viewModel.movieResult.observe(viewLifecycleOwner) {
            when(it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let { data -> imageListAdapter.setData(data.images) }
                }
                Resource.Status.LOADING -> {

                }
                Resource.Status.ERROR -> {
                    onError(it.message)
                }
            }
        }
    }

}