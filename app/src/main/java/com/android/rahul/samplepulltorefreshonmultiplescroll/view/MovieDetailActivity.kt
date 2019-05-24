package com.android.rahul.samplepulltorefreshonmultiplescroll.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.android.rahul.samplepulltorefreshonmultiplescroll.R
import com.android.rahul.samplepulltorefreshonmultiplescroll.model.Result
import com.android.rahul.samplepulltorefreshonmultiplescroll.viewmodel.MovieDetailsViewModel
import androidx.databinding.DataBindingUtil
import com.android.rahul.samplepulltorefreshonmultiplescroll.controller.Navigator
import com.android.rahul.samplepulltorefreshonmultiplescroll.databinding.ActivityMovieDetailBinding


class MovieDetailActivity : AppCompatActivity() {
    var viewModel: MovieDetailsViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        viewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java)
        addFragment()
        supportActionBar?.title = getString(R.string.movie_details_head)

    }

    // add fragment to the activity.
    private fun addFragment() {
        Navigator.addFragment(this, MovieDetailFragment::class.java.name, false, intent.extras, "mainList")

    }
}
