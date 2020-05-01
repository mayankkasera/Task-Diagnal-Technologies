package com.example.task_diagnal_technologies.ui

import com.example.task_diagnal_technologies.api.pojo.RomanticComedy

sealed class RomanticComedystate {

    data class Succes(var responce : RomanticComedy) : RomanticComedystate()
    data class Failure(var message : String) : RomanticComedystate()

}