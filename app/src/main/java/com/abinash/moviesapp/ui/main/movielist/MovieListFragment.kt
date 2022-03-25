package com.abinash.moviesapp.ui.main.movielist

import android.os.Bundle
import android.view.View
import com.abinash.moviesapp.R
import com.abinash.moviesapp.databinding.FragmentMovieListBinding
import com.abinash.moviesapp.di.scopes.FragmentScoped
import com.abinash.moviesapp.ui.main.BaseMainFragment
import com.abinash.moviesapp.ui.main.movielist.adapter.MovieListAdapter
import com.abinash.moviesapp.utils.Resource

@FragmentScoped
class MovieListFragment :
    BaseMainFragment<MovieListViewModel, FragmentMovieListBinding>(R.layout.fragment_movie_list) {

    val movieListAdapter: MovieListAdapter = MovieListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initiateViewModels()
    }

    private fun initViews() {
        binding.apply {
            rvMovieList.adapter = movieListAdapter
        }
        movieListAdapter.apply {
            onWatchListClickEvent.observe(viewLifecycleOwner) {
                it.watched = !it.watched
                viewModel.updateMovie(it)
            }
            watchPostersClickEvent.observe(viewLifecycleOwner) {
                val bundle = Bundle().apply {
                    putString("MOVIE_ID", it.id)

                }
                navigationViewModel.navigateTo(R.id.action_movieListFragment_to_movieFragment, bundle)
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }


    private fun initiateViewModels() {

        viewModel.movieList.observe(viewLifecycleOwner) {
            when(it.status) {
                Resource.Status.SUCCESS -> {
                    it.data?.let { data -> movieListAdapter.setData(data) }
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