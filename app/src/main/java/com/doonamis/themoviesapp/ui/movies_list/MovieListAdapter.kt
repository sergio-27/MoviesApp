package com.doonamis.themoviesapp.ui.movies_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.doonamis.themoviesapp.R
import com.doonamis.themoviesapp.databinding.ItemPopularTvshowBinding
import com.doonamis.themoviesapp.model.TvShow
import com.doonamis.themoviesapp.utils.URLs
import javax.inject.Inject

class MovieListAdapter @Inject constructor(
    private var context: Context,
    private var list: MutableList<TvShow>
) : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    inner class MovieListViewHolder(private val itemBinding: ItemPopularTvshowBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(tvShow: TvShow) = with(itemView) {
            itemBinding.tvTvshowName.text = tvShow.name.ifEmpty { "No data" }
            itemBinding.tvTvshowAirdate.text = context.resources.getString(R.string.airdate,  tvShow.first_air_date)
            itemBinding.tvTvshowOverview.text = tvShow.overview.ifEmpty { "No data" }
            itemBinding.tvTvshowName.text = tvShow.name

            if (tvShow.poster_path.isNullOrEmpty()) {
                itemBinding.ivPoster.visibility = View.GONE
                itemBinding.tvNoImage.visibility = View.VISIBLE
            } else {
                itemBinding.ivPoster.visibility = View.VISIBLE
                itemBinding.tvNoImage.visibility = View.GONE
                itemBinding.ivPoster.setImageFromUrl(
                    context,
                    "${URLs.base_image_url}${tvShow.poster_path}"
                )

            }
        }
    }

    interface OnPopularTvShowClickListener {
        fun onPopularTvShowClicked(
            tvShow: TvShow
        )
    }

    fun appendMovies(movies: List<TvShow>) {
        this.list.addAll(movies)
        notifyItemRangeInserted(
            this.list.size,
            movies.size -1
        )
    }

    private var onTvShowClickListener: OnPopularTvShowClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        // Create a new view, which defines the UI of the list item
        val itemBinding =
            ItemPopularTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        holder.bind(list[position])
        val popularTvShow = list[position]

        holder.itemView.setOnClickListener {
            onTvShowClickListener!!.onPopularTvShowClicked(popularTvShow)
        }
    }

    override fun getItemCount(): Int = list.size

    fun setOnPopularTvShowClickListener(onPopularTvShowClickListener: OnPopularTvShowClickListener) {
        this.onTvShowClickListener = onPopularTvShowClickListener
    }
}

fun ImageView.setImageFromUrl(context: Context, imageUrl: String) {
    Glide.with(context).load(imageUrl)
        .into(this)
}