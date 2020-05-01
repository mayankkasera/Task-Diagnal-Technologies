package com.example.task_diagnal_technologies.api.romantic_comedy

import com.example.task_diagnal_technologies.api.pojo.RomanticComedy
import io.reactivex.Observable

interface RomanticComedyRepositoryI {
    fun getRomanticComedy(page : String) : Observable<RomanticComedy>
}