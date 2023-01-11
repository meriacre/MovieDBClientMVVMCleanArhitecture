package com.test.tmdbclient.presentation.artist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.tmdbclient.R
import com.test.tmdbclient.data.model.artist.Artist
import com.test.tmdbclient.databinding.ActivityArtistBinding
import com.test.tmdbclient.presentation.di.Injector
import com.test.tmdbclient.presentation.movie.MovieAdapter
import com.test.tmdbclient.presentation.movie.MovieViewModel
import com.test.tmdbclient.presentation.movie.MovieViewModelFactory
import javax.inject.Inject

class ArtistActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ArtistViewModelFactory
    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var myAdapter: ArtistAdapter
    private lateinit var binding: ActivityArtistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artist)

        (application as Injector).createArtistSubComponent()
            .inject(this)

        artistViewModel = ViewModelProvider(this, factory)
            .get(ArtistViewModel::class.java)

        initRecycleView()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.update, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when(item.itemId){
            R.id.action_update -> {
                updateArtists()
                true
            }else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun initRecycleView(){
        binding.rvArtist.layoutManager = LinearLayoutManager(this)
        myAdapter = ArtistAdapter()
        binding.rvArtist.adapter = myAdapter
        displayPopularMovies()
    }

    private fun displayPopularMovies(){
        binding.progressBar.visibility = View.VISIBLE
        val responseLiveDAta = artistViewModel.getArtists()
        responseLiveDAta.observe(this, Observer {
            if (it!= null){
                myAdapter.setList(it)
                myAdapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE
            }else{
                binding.progressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No data available!", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun updateArtists(){
        binding.progressBar.visibility = View.VISIBLE
        val response = artistViewModel.updateArtists()
        response.observe(this, Observer {
            if (it != null){
                myAdapter.setList(it)
                myAdapter.notifyDataSetChanged()
                binding.progressBar.visibility = View.GONE
            }else{
                binding.progressBar.visibility = View.GONE
                Toast.makeText(applicationContext, "No data available!", Toast.LENGTH_LONG).show()
            }
        })
    }
}