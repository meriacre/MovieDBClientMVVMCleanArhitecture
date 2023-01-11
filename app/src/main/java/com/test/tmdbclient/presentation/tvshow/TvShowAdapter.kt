package com.test.tmdbclient.presentation.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.tmdbclient.R
import com.test.tmdbclient.data.model.artist.Artist
import com.test.tmdbclient.data.model.tvshow.TvShow
import com.test.tmdbclient.databinding.ListItemBinding

class TvShowAdapter: RecyclerView.Adapter<MyViewHolder>() {

    private var tvShowList = ArrayList<TvShow>()

    fun setList(tvShows: List<TvShow>){
        tvShowList.clear()
        tvShowList = ArrayList(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.bind(tvShowList[position])
    }

    override fun getItemCount(): Int = tvShowList.size

}

class MyViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
    fun bind(tvShow: TvShow){
        binding.titleTextView.text = tvShow.name
        binding.descriptionTextView.text = tvShow.overview
        val posterURL = "https://image.tmdb.org/t/p/w500" + tvShow.posterPath
        Glide.with(binding.imageView.context)
            .load(posterURL)
            .into(binding.imageView)
    }
}