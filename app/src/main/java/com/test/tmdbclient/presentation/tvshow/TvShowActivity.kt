package com.test.tmdbclient.presentation.tvshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.tmdbclient.R
import com.test.tmdbclient.databinding.ActivityTvShowBinding
import com.test.tmdbclient.presentation.di.Injector
import javax.inject.Inject

class TvShowActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: TvShowViewModelFactory
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var myAdapter: TvShowAdapter
    private lateinit var binding: ActivityTvShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show)

        (application as Injector).createTvShowSubComponent()
            .inject(this)

        tvShowViewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

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
                updateTvShows()
                true
            }else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun initRecycleView(){
        binding.rvTvShow.layoutManager = LinearLayoutManager(this)
        myAdapter = TvShowAdapter()
        binding.rvTvShow.adapter = myAdapter
        displayPopularMovies()
    }

    private fun displayPopularMovies(){
        binding.pbTvShow.visibility = View.VISIBLE
        val responseLiveDAta = tvShowViewModel.getTvShows()
        responseLiveDAta.observe(this, Observer {
            if (it!= null){
                myAdapter.setList(it)
                myAdapter.notifyDataSetChanged()
                binding.pbTvShow.visibility = View.GONE
            }else{
                binding.pbTvShow.visibility = View.GONE
                Toast.makeText(applicationContext, "No data available!", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun updateTvShows(){
        binding.pbTvShow.visibility = View.VISIBLE
        val response = tvShowViewModel.updateTvShows()
        response.observe(this, Observer {
            if (it != null){
                myAdapter.setList(it)
                myAdapter.notifyDataSetChanged()
                binding.pbTvShow.visibility = View.GONE
            }else{
                binding.pbTvShow.visibility = View.GONE
                Toast.makeText(applicationContext, "No data available!", Toast.LENGTH_LONG).show()
            }
        })
    }
}