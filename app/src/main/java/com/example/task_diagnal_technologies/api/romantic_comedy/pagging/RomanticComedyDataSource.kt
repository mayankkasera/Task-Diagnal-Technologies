package com.example.task_diagnal_technologies.api.romantic_comedy.pagging


import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.example.task_diagnal_technologies.api.pojo.RomanticComedy
import com.example.task_diagnal_technologies.api.romantic_comedy.RomanticComedyRepositoryI
import com.example.task_diagnal_technologies.ui.RomanticComedystate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RomanticComedyDataSource(
    var compositeDisposable: CompositeDisposable,
    val romanticComedyRepositoryI: RomanticComedyRepositoryI
) : PageKeyedDataSource<Int, RomanticComedy.Page.ContentItems.Content>()  {

    val FIRST_PAGE = 1
    var list : MutableList<RomanticComedy.Page.ContentItems.Content> = mutableListOf()

    val mutableLiveData: MutableLiveData<RomanticComedystate> = MutableLiveData()


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, RomanticComedy.Page.ContentItems.Content>
    ) {
        compositeDisposable.add(
            romanticComedyRepositoryI.getRomanticComedy("CONTENTLISTINGPAGE-PAGE"+FIRST_PAGE+".json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    for (x in 0 until it.page.contentItems.content.size){
                        var p : RomanticComedy.Page.ContentItems.Content = it.page.contentItems.content.get(x)
                        p.id = (20*(FIRST_PAGE-1))+x
                        it.page.contentItems.content[x] = p
                    }

                    list.addAll(it.page.contentItems.content)
                    publishState(RomanticComedystate.Succes(list))
                    callback.onResult(it.page.contentItems.content, null, FIRST_PAGE + 1)
                },{
                    publishState(RomanticComedystate.Failure(it.message!!))
                },{

                },{

                }))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, RomanticComedy.Page.ContentItems.Content>) {
        compositeDisposable.add(
            romanticComedyRepositoryI.getRomanticComedy("CONTENTLISTINGPAGE-PAGE"+"${params.key}"+".json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    for (x in 0 until it.page.contentItems.content.size){
                        var p : RomanticComedy.Page.ContentItems.Content = it.page.contentItems.content.get(x)
                        p.id = (20*((params.key)-1))+x
                        it.page.contentItems.content[x] = p
                    }
                    if (3 >= params.key) {
                        list.addAll(it.page.contentItems.content)
                        publishState(RomanticComedystate.Succes(list))
                        callback.onResult(it.page.contentItems.content, params.key + 1)
                    }
                },{

                },{

                },{

                }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, RomanticComedy.Page.ContentItems.Content>) {
        compositeDisposable.add(
            romanticComedyRepositoryI.getRomanticComedy("CONTENTLISTINGPAGE-PAGE"+"${params.key}"+".json")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    for (x in 0 until it.page.contentItems.content.size){
                        var p : RomanticComedy.Page.ContentItems.Content = it.page.contentItems.content.get(x)
                        val i: Int = if (params.key > 1) params.key - 1 else 0
                        p.id = (20*(i-1))+x
                        it.page.contentItems.content[x] = p
                    }
                    if (it != null) {

                        list.addAll(it.page.contentItems.content)
                        publishState(RomanticComedystate.Succes(list))

                        val i: Int = if (params.key > 1) params.key - 1 else 0
                        callback.onResult(it.page.contentItems.content, i)
                    }
                },{

                },{

                },{

                }))
    }


    private fun publishState(state: RomanticComedystate) {
        mutableLiveData.postValue(state)
    }

}