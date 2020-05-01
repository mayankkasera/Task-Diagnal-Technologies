package com.example.task_diagnal_technologies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.task_diagnal_technologies.R
import com.example.task_diagnal_technologies.api.pojo.RomanticComedy
import com.example.task_diagnal_technologies.ui.adapter.RomanticComedyAdapter
import com.example.task_diagnal_technologies.utils.SpacingItemDecoration
import com.example.task_diagnal_technologies.utils.Util
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.recyclerview

class SearchActivity : AppCompatActivity() {

    lateinit var list:List<RomanticComedy.Page.ContentItems.Content>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        recyclerview.setLayoutManager(GridLayoutManager(this, 3))
        recyclerview.addItemDecoration(SpacingItemDecoration(3, Util.dpToPx(this, 10), true))


        list = intent.getParcelableArrayListExtra<RomanticComedy.Page.ContentItems.Content>("list")


        edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s.toString().length>3){
                    val l = mutableListOf<RomanticComedy.Page.ContentItems.Content>()
                    for(x in list){
                        if(x.name.toLowerCase().matches("(.*)${s.toString().toLowerCase()}(.*)".toRegex())){
                            l.add(x)
                        }
                    }
                    recyclerview.adapter = RomanticComedyAdapter(this@SearchActivity,l)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

    }
}
