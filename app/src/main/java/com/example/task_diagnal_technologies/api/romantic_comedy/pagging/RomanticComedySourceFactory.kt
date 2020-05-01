package com.example.task_diagnal_technologies.api.romantic_comedy.pagging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.task_diagnal_technologies.api.pojo.RomanticComedy
import com.example.task_diagnal_technologies.api.romantic_comedy.RomanticComedyRepositoryI
import io.reactivex.disposables.CompositeDisposable


class RomanticComedySourceFactory(
        var compositeDisposable: CompositeDisposable,
        val romanticComedyRepositoryI: RomanticComedyRepositoryI
    ) : DataSource.Factory<Int, RomanticComedy.Page.ContentItems.Content>() {

    val moviesLiveDataSource =  MutableLiveData<RomanticComedyDataSource>()

    override fun create(): DataSource<Int, RomanticComedy.Page.ContentItems.Content> {
        val personDataSource =
            RomanticComedyDataSource(
                compositeDisposable,
                romanticComedyRepositoryI
            )
        moviesLiveDataSource.postValue(personDataSource)
        return personDataSource
    }


}