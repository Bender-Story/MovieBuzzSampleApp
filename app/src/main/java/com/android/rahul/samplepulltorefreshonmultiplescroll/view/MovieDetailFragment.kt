package com.android.rahul.samplepulltorefreshonmultiplescroll.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.android.rahul.samplepulltorefreshonmultiplescroll.model.Result
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.rahul.samplepulltorefreshonmultiplescroll.R
import com.android.rahul.samplepulltorefreshonmultiplescroll.components.ActivityUIExt
import com.android.rahul.samplepulltorefreshonmultiplescroll.databinding.MovieDetailsFragmentBinding
import com.android.rahul.samplepulltorefreshonmultiplescroll.foundation.BaseFragment
import com.android.rahul.samplepulltorefreshonmultiplescroll.viewmodel.MovieDetailsViewModel
import com.android.rahul.samplepulltorefreshonmultiplescroll.viewmodel.MoviewRelatedRowViewModel
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.movie_details_fragment.*

class MovieDetailFragment : BaseFragment() {


    private lateinit var binding: MovieDetailsFragmentBinding
    private lateinit var movieDetailsViewModel: MovieDetailsViewModel
    var result: Result? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.movie_details_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieDetailsViewModel = ViewModelProviders.of(activity!!).get(MovieDetailsViewModel::class.java)
        binding.viewModel = movieDetailsViewModel
        binding.lifecycleOwner = this
        observeList()
        getMovieDetails()
        callService()
    }

    // Observers th mutable list to update recycler view
    private fun observeList() {
        movieDetailsViewModel?.movieResults?.observe(this,
            Observer {
                initRecyclerView(it)
            })
    }

    private fun getMovieDetails() {
        result = activity?.intent?.extras?.getSerializable("result") as Result
        movieDetailsViewModel?.result?.postValue(result)
    }

    fun initRecyclerView(data: List<Result>?) {
        var rowViewModels: ArrayList<MoviewRelatedRowViewModel>? = arrayListOf()
        data?.map {
            rowViewModels?.add(MoviewRelatedRowViewModel(it) { result ->
                movieDetailsViewModel?.result?.postValue(result)
                callService()
            })
        }

        detailsRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        detailsRecyclerView.adapter = MovieDetailsAdapter(rowViewModels)
    }

    fun callService() {
        activity?.progressbar_details?.isVisible = true
        movieDetailsViewModel?.fetchRelatedResults(result?.id.toString(),
            { activity?.progressbar_details?.isVisible = false },
            {
                activity?.progressbar_details?.isVisible = false
                ActivityUIExt(activity as Context).buildDialog(it) {
                    callService()
                }
            })
    }
}