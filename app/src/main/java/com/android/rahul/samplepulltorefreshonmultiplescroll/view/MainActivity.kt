package com.android.rahul.samplepulltorefreshonmultiplescroll.view


import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.rahul.samplepulltorefreshonmultiplescroll.R
import com.android.rahul.samplepulltorefreshonmultiplescroll.components.ActivityUIExt
import com.android.rahul.samplepulltorefreshonmultiplescroll.controller.Navigator
import com.android.rahul.samplepulltorefreshonmultiplescroll.foundation.BaseActivity
import com.android.rahul.samplepulltorefreshonmultiplescroll.model.Result
import com.android.rahul.samplepulltorefreshonmultiplescroll.viewmodel.MainRowViewModel
import com.android.rahul.samplepulltorefreshonmultiplescroll.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import android.view.Gravity
import android.R.attr.gravity
import android.widget.RelativeLayout
import android.widget.LinearLayout


class MainActivity : BaseActivity() {
    var viewModel: MainViewModel? = null
    private var rowViewModels: ArrayList<MainRowViewModel>? = arrayListOf()

    var progressRowViewModel :MainRowViewModel= MainRowViewModel(null,true,{})
    var firstCall:Boolean= false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        observeList()
        callService()


    }

    // Observers th mutable list to update recycler view
    private fun observeList() {
        viewModel?.movieResults?.observe(this,
            Observer {
                initRecyclerView(it)
            })
        supportActionBar?.title = getString(R.string.main_list_head)
    }

    private fun initRecyclerView(data: List<Result>?) {


        data?.map {
           var mainViewModel : MainRowViewModel =MainRowViewModel(it) { result ->
               var bundle = Bundle()
               bundle.putSerializable("result", result)
               Navigator.goToActivity(this, MovieDetailActivity::class.java, bundle = bundle)
           }
            if(firstCall)
                mainRecyclerView.adapter = MainAdapter(rowViewModels)
            if(mainRecyclerView.adapter!=null) {
                (mainRecyclerView.adapter as MainAdapter).add(mainViewModel)
            }
        }
        if(firstCall) {
            mainRecyclerView.layoutManager = LinearLayoutManager(this)
            setRecyclerViewScrollListener()
        }

    }

    // fetch list data from the service
    private fun callService() {
        firstCall = viewModel?.fetchPage!! == 1

        if(firstCall)progressbar.isVisible = true
        viewModel?.fetchMovieResults({
            stopProgressBar()
        }, {
            stopProgressBar()
            ActivityUIExt(this).buildDialog(it) {
                callService()
            }
        })
    }
    private fun stopProgressBar(){
        progressbar.isVisible = false
        if(mainRecyclerView.adapter!=null) {
            (mainRecyclerView.adapter as MainAdapter).remove(progressRowViewModel)
        }
    }



    private fun setRecyclerViewScrollListener() {
        var scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
               var canScrollMore= mainRecyclerView.canScrollVertically(1)
                if(!canScrollMore){
                   if(!viewModel?.isMaxPageLimitReached()!! &&
                       (mainRecyclerView.adapter as MainAdapter).addProgressBar(progressRowViewModel))
                    callService()
                }
            }
        }
        mainRecyclerView.addOnScrollListener(scrollListener)
    }


}
