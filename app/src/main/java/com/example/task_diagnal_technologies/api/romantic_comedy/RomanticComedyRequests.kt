package com.example.task_diagnal_technologies.api.romantic_comedy

import com.example.task_diagnal_technologies.api.pojo.RomanticComedy
import retrofit2.Call
import retrofit2.http.GET

interface RomanticComedyRequests {

//    https://raw.githubusercontent.com/mayankkasera/Task-Diagnal-Technologies/master/api/CONTENTLISTINGPAGE-PAGE1.json

    @GET("CONTENTLISTINGPAGE-PAGE1.json")
    fun getRomanticComedy(): Call<RomanticComedy>
}