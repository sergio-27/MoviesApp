package com.doonamis.themoviesapp.ui.movies_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.doonamis.themoviesapp.R
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    companion object {
        const val TAG = "MovieListFragment"
    }

    private val viewModel: MovieListViewModel by viewModels()
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.onInit()


        viewModel.popularTvShowsLiveData.observe(this, Observer {
            Log.d(TAG, it.size.toString())
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_movie_list, container, false)

//        viewModel.getPopularTvShows()
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                Log.d(TAG, it.size.toString())
//            }



        // Inflate the layout for this fragment
        return view
    }


}