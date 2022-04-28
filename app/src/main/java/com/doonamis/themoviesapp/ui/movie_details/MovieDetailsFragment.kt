package com.doonamis.themoviesapp.ui.movie_details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.doonamis.themoviesapp.R
import com.doonamis.themoviesapp.databinding.FragmentMovieDetailsBinding
import com.doonamis.themoviesapp.model.TvShow
import com.doonamis.themoviesapp.ui.movies_list.setImageFromUrl
import com.doonamis.themoviesapp.utils.URLs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var binding: FragmentMovieDetailsBinding? = null
    private val args: MovieDetailsFragmentArgs by navArgs()
    private lateinit var tvShowSelected: TvShow

    companion object {
        const val TAG = "MovieDetailsFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val view: View = binding!!.root
        tvShowSelected = args.tvshow

        binding!!.ivTvshow.setImageFromUrl(requireContext(), "${ URLs.base_image_url}${tvShowSelected.backdrop_path}")
        binding!!.tvTvshowName.text = tvShowSelected.name
        binding!!.tvTvshowAirdate.text = getString(R.string.airdate, tvShowSelected.first_air_date)
        binding!!.tvTvshowVoteAvg.text = getString(R.string.tv_show_vote_average, tvShowSelected.vote_average)
        binding!!.tvTvshowVoteCount.text = getString(R.string.tv_show_vote_count, tvShowSelected.vote_count)
        binding!!.tvTvshowOverview.text = tvShowSelected.overview

        Log.d(TAG, tvShowSelected.name)
        // Inflate the layout for this fragment
        return view
    }


}