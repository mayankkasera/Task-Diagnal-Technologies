package com.example.task_diagnal_technologies.api

import com.example.task_diagnal_technologies.api.romantic_comedy.RomanticComedyRepositoryI
import com.example.task_diagnal_technologies.utils.App
import javax.inject.Inject

class DataHelper {

    init {
        App.networkComponent()?.inject(this)
    }

    @Inject
    lateinit var romanticComedyRepositoryI: RomanticComedyRepositoryI

}