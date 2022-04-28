package com.doonamis.themoviesapp.ui.movies_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doonamis.themoviesapp.databinding.FragmentMovieListBinding
import com.doonamis.themoviesapp.model.TvShow
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment(), MovieListAdapter.OnPopularTvShowClickListener {

    companion object {
        const val TAG = "MovieListFragment"
    }

    private lateinit var popularTvShowAdapter: MovieListAdapter

    private var binding: FragmentMovieListBinding? = null
    private val viewModel: MovieListViewModel by viewModels()
    private var page: Int = 1
    private var isLoading: Boolean = false
    private var popularTvShows = mutableListOf<TvShow>()
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.popularTvShowsLiveData.observe(this) {
            updateList(it)
            isLoading = false
        }

        //getPopularTvShows()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieListBinding.inflate(inflater, container, false)
        val view: View = binding!!.root


        setRecyclerView()
//        viewModel.getPopularTvShows()
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                Log.d(TAG, it.size.toString())
//            }


        // Inflate the layout for this fragment
        return view
    }

    private fun getPopularTvShows(isInitLoad: Boolean = false) {
        if (isInitLoad){
            page = 1
            viewModel.getPopularTvShows(page)
        } else {
            page+=1
            viewModel.getPopularTvShows(page)
        }

        Log.d(TAG, "page $page")

    }

    fun updateList(newList: List<TvShow>) {
        val mutableList = newList.toMutableList()
        popularTvShowAdapter.appendMovies(mutableList)
        popularTvShows = mutableList
    }


    override fun onDestroy() {
        super.onDestroy()
        page = 1
        binding = null
    }

    override fun onPopularTvShowClicked(
        tvShow: TvShow
    ) {

        val directions =
            MovieListFragmentDirections.actionMoviesListFragmentToMovieDetailFragment(tvShow)
        findNavController().navigate(directions)
    }


    private fun attachPopularTvShowsOnScrollListener() {
        //binding!!.rvPopularTvShows.addOnScrollListener(n )
    }

    private fun setRecyclerView() {
        popularTvShowAdapter = MovieListAdapter(requireContext(), mutableListOf())
        linearLayoutManager = LinearLayoutManager(context)

        getPopularTvShows(isInitLoad = true)

        //popularTvShowAdapter.appendMovies(list)
        binding!!.rvPopularTvShows.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = popularTvShowAdapter
        }

        binding!!.rvPopularTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!isLoading) {
                    if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() == popularTvShows.size - 1) {
                        //load data
                        getPopularTvShows()
                        isLoading = true

                    }
                }
            }
        })

        popularTvShowAdapter.registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                linearLayoutManager.scrollToPositionWithOffset(positionStart, 0)
            }
        })

        popularTvShowAdapter.setOnPopularTvShowClickListener(this)
    }
}