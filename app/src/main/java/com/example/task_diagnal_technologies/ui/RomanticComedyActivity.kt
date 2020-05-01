package com.example.task_diagnal_technologies.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.task_diagnal_technologies.R
import com.example.task_diagnal_technologies.api.DataHelper
import com.example.task_diagnal_technologies.api.pojo.RomanticComedy
import com.example.task_diagnal_technologies.ui.adapter.RomanticComedyAdapter
import com.example.task_diagnal_technologies.ui.adapter.RomanticComedyPaggingAdapter
import com.example.task_diagnal_technologies.utils.*
import kotlinx.android.synthetic.main.activity_main.*


class RomanticComedyActivity : AppCompatActivity() {

    lateinit var romanticComedyViewModel: RomanticComedyViewModel
    var list : MutableList<RomanticComedy.Page.ContentItems.Content> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        loadData()
        setObserver()

        search.setOnClickListener{
            val intent = Intent(this,SearchActivity::class.java)
            intent.putParcelableArrayListExtra("list", ArrayList<Parcelable>(list))
            startActivity(intent)
        }

    }

    private fun loadData() {
        loader.visible()
        romanticComedyViewModel.getRomanticComedy()
    }

    private fun init() {
        val factory = RomanticComedyViewModel(DataHelper().romanticComedyRepositoryI).createFactory()
        romanticComedyViewModel = ViewModelProvider(this, factory).get(RomanticComedyViewModel::class.java)

        recyclerview.setLayoutManager(GridLayoutManager(this, 3))
        recyclerview.addItemDecoration(SpacingItemDecoration(3, Util.dpToPx(this, 10), true))

    }

    private fun setObserver() {

        var adapter = RomanticComedyPaggingAdapter()
        recyclerview.adapter = adapter

        romanticComedyViewModel.state.observe(this, Observer {
            when(it){
                is RomanticComedystate.Succes -> {
                   // recyclerview.adapter = RomanticComedyAdapter(this,it.responce.page.contentItems.content)
                    loader.gone()
                    list = it.list
                }
                is RomanticComedystate.Failure -> {
                    loader.gone()
                    Log.i("sdcbdj",it.message)
                    Toast.makeText(this,it.message, Toast.LENGTH_LONG).show()
                }
            }
        })

        romanticComedyViewModel.moviePagedList.observe(this, Observer {
            adapter.submitList(it!!)
        })

    }
}
