package com.abinash.moviesapp.ui.main.movielist.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.abinash.moviesapp.R
import com.abinash.moviesapp.di.data.model.Movie
import com.bumptech.glide.Glide
import com.abinash.moviesapp.utils.SingleLiveEvent

class MovieListAdapter() :
    RecyclerView.Adapter<MovieListAdapter.MyViewHolder>() {

    private var mMovieList = listOf<Movie>()

    private var listWidth = 0

    var onWatchListClickEvent = SingleLiveEvent<Movie>()
    var watchPostersClickEvent = SingleLiveEvent<Movie>()

    fun setData(data: List<Movie>) {
        mMovieList = data
        notifyDataSetChanged()
    }


//    val onEditorAction = SingleLiveEvent<EditorAction>()

    class MyViewHolder(
        itemView: View,
//        val onEditorAction: SingleLiveEvent<EditorAction>,
//        val activeLayerData: LiveData<Layer?>
    ) :
        RecyclerView.ViewHolder(itemView) {

        @SuppressLint("DefaultLocale", "UseCompatLoadingForDrawables")
        fun bind(
            item: Movie,
            position: Int,
            onWatchListClickEvent: SingleLiveEvent<Movie>,
            watchPostersClickEvent: SingleLiveEvent<Movie>,
        ) {
            val imageView = itemView.findViewById<ImageView>(R.id.poster)
            val rating = itemView.findViewById<TextView>(R.id.rating)
            val fullTitle = itemView.findViewById<TextView>(R.id.full_title)
            val watchPosters = itemView.findViewById<TextView>(R.id.watch_posters)
            val addToWatch = itemView.findViewById<AppCompatButton>(R.id.add_to_watch)

            addToWatch.text =
                if (item.watched) itemView.resources.getText(R.string.remove_from_watch) else
                    itemView.resources.getText(R.string.add_to_watch)


            watchPosters.setOnClickListener {
                watchPostersClickEvent.postValue(item)
            }

            addToWatch.setOnClickListener {
                onWatchListClickEvent.postValue(item)
            }

            rating.text = item.IMDbRating
            fullTitle.text = item.fullTitle

            Glide.with(itemView.context).load(item.image)
                .into(imageView)

            imageView.setOnClickListener { onClickPage() }
        }

        fun onClickPage() {
            val orientation = itemView.resources.configuration.orientation

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MyViewHolder(
            itemView
        )
    }

    override fun getItemCount(): Int {
        return mMovieList.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(
            mMovieList[position],
            position,
            onWatchListClickEvent,
            watchPostersClickEvent
        )
    }


}