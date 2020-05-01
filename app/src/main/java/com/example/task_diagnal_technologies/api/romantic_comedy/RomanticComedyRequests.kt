package com.example.task_diagnal_technologies.api.romantic_comedy

import com.example.task_diagnal_technologies.api.pojo.RomanticComedy
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RomanticComedyRequests {

//    https://raw.githubusercontent.com/mayankkasera/Task-Diagnal-Technologies/master/api/CONTENTLISTINGPAGE-PAGE1.json

//    CONTENTLISTINGPAGE-PAGE1.json


    @GET("api/{page}")
    fun getRomanticComedy(@Path("page")page : String): Call<RomanticComedy>
}