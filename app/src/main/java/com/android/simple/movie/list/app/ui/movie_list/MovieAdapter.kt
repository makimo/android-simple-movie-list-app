package com.android.simple.movie.list.app.ui.movie_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.simple.movie.list.app.R
import com.android.simple.movie.list.app.data.models.Movie
import com.android.simple.movie.list.app.uitls.format
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movies = ArrayList<Movie>()

    fun setData(data: List<Movie>) {
        movies.clear()
        movies.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(item)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(movies[position])

    override fun getItemCount() = movies.size

    override fun getItemId(position: Int) = position.toLong()

    inner class MovieViewHolder(val item: View) : RecyclerView.ViewHolder(item) {
        fun bind(movie: Movie) {
            item.apply {
                txt_title.text = movie.title
                txt_year.format(R.string.year, movie.year)
                val directorRes = if (movie.directors.size == 1) R.string.director else R.string.directors
                txt_directors.format(directorRes, movie.directors.joinToString { it })
                val genresRes = if (movie.genres.size == 1) R.string.genre else R.string.genres
                txt_genres.format(genresRes, movie.genres.joinToString { it.name })
            }
        }
    }
}