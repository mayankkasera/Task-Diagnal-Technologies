package com.example.task_diagnal_technologies.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.task_diagnal_technologies.api.pojo.RomanticComedy
import com.example.task_diagnal_technologies.api.romantic_comedy.RomanticComedyRepositoryI
import com.example.task_diagnal_technologies.api.romantic_comedy.pagging.RomanticComedyDataSource
import com.example.task_diagnal_technologies.api.romantic_comedy.pagging.RomanticComedySourceFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RomanticComedyViewModel(val romanticComedyRepositoryI: RomanticComedyRepositoryI) : ViewModel(){

    lateinit var moviePagedList: LiveData<PagedList<RomanticComedy.Page.ContentItems.Content>>
    lateinit var state : LiveData<RomanticComedystate>
    private var compositeDisposable = CompositeDisposable()

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(20)
        .build()

    fun getRomanticComedy() {

        var romanticComedySourceFactory = RomanticComedySourceFactory(compositeDisposable,romanticComedyRepositoryI)
        moviePagedList = LivePagedListBuilder(romanticComedySourceFactory, config).build()
        state = Transformations.switchMap<RomanticComedyDataSource, RomanticComedystate>(
            romanticComedySourceFactory.moviesLiveDataSource, RomanticComedyDataSource::mutableLiveData)

    }



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }


}