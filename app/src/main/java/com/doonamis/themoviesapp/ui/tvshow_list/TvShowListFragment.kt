package com.doonamis.themoviesapp.ui.tvshow_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doonamis.themoviesapp.R
import com.doonamis.themoviesapp.databinding.FragmentTvshowListBinding
import com.doonamis.themoviesapp.model.TvShow
import com.doonamis.themoviesapp.ui.MainActivity
import com.doonamis.themoviesapp.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvShowListFragment : Fragment(), TvShowListAdapter.OnPopularTvShowClickListener {

    companion object {
        const val TAG = "MovieListFragment"
    }

    private lateinit var popularTvShowAdapter: TvShowListAdapter

    private var binding: FragmentTvshowListBinding? = null
    private val viewModel: TvShowListViewModel by viewModels()

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvshowListBinding.inflate(inflater, container, false)
        val view: View = binding!!.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init recycler view and set scroll listener
        setRecyclerView()


        getPopularTvShows()

        (activity as MainActivity).supportActionBar?.title = getString(R.string.app_name)

        viewModel.popularTvShowsLiveData.observe(viewLifecycleOwner) {
            updateList(it)
        }

    }


    private fun getPopularTvShows() {
        viewModel.getPopularTvShows()
    }

    private fun updateList(newList: List<TvShow>) {
        val mutableList = newList.toMutableList()
        popularTvShowAdapter.appendMovies(mutableList)
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onResume() {
        super.onResume()
        popularTvShowAdapter.clearAdapterList()
    }

    override fun onPopularTvShowClicked(
        tvShow: TvShow
    ) {

        //reset page counter to zero to show first item from tvshows list
        viewModel.setPageToZero()

        val directions =
            TvShowListFragmentDirections.actionTvshowListFragmentToTvshowDetailFragment(tvShow)
        findNavController().navigate(directions)
    }

    private fun setRecyclerView() {
        popularTvShowAdapter = TvShowListAdapter(requireContext(), mutableListOf())
        linearLayoutManager = LinearLayoutManager(context)

        binding!!.rvPopularTvShows.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = popularTvShowAdapter
        }

        binding!!.rvPopularTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (Utils.isLastItemDisplaying(recyclerView)) {
                    getPopularTvShows()
                }

            }
        })

        popularTvShowAdapter.setOnPopularTvShowClickListener(this)
    }
}