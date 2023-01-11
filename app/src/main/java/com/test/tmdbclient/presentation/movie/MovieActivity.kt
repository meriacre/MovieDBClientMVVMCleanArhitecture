package com.test.tmdbclient.presentation.movie

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
import com.test.tmdbclient.databinding.ActivityMovieBinding
import com.test.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class MovieActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var myAdapter: MovieAdapter
    private lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie)
        (application as Injector).createMovieSubComponent()
            .inject(this)

        movieViewModel = ViewModelProvider(this, factory)
            .get(MovieViewModel::class.java)

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
                updateMovies()
                true
            }else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun initRecycleView(){
        binding.rvMovie.layoutManager = LinearLayoutManager(this)
        myAdapter = MovieAdapter()
        binding.rvMovie.adapter = myAdapter
        displayPopularMovies()
    }

    private fun displayPopularMovies(){
        binding.progressBar.visibility = View.VISIBLE
        val responseLiveDAta = movieViewModel.getMovies()
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

    private fun updateMovies(){
        binding.progressBar.visibility = View.VISIBLE
        val response = movieViewModel.updateMovies()
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