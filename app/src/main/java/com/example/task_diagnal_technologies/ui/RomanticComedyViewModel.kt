package com.example.task_diagnal_technologies.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task_diagnal_technologies.api.romantic_comedy.RomanticComedyRepositoryI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RomanticComedyViewModel(val romanticComedyRepositoryI: RomanticComedyRepositoryI) : ViewModel(){

    var mutableLiveData: MutableLiveData<RomanticComedystate> = MutableLiveData()
    private var compositeDisposable = CompositeDisposable()

    fun getRomanticComedy() {
        compositeDisposable.add(
            romanticComedyRepositoryI.getRomanticComedy()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    publishState(RomanticComedystate.Succes(it))
                },{
                    publishState(RomanticComedystate.Failure(it.message!!))
                },{

                },{

                })
        )
    }



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    private fun publishState(state: RomanticComedystate) {
        mutableLiveData.postValue(state)
    }
}