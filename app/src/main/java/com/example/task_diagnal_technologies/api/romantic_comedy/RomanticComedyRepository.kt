package com.example.task_diagnal_technologies.api.romantic_comedy

import android.util.Log
import com.example.task_diagnal_technologies.api.pojo.RomanticComedy
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RomanticComedyRepository(var romanticComedyRequests: RomanticComedyRequests) : RomanticComedyRepositoryI{
    override fun getRomanticComedy(page : String): Observable<RomanticComedy> {
        return Observable.create<RomanticComedy> { emitter ->
            romanticComedyRequests.getRomanticComedy(page).enqueue(object :
                Callback<RomanticComedy> {
                override fun onResponse(call: Call<RomanticComedy>, response: Response<RomanticComedy>) {
                    Log.i("kdsjcn", "onResponse  body : " + response.body().toString())
                    Log.i("kdsjcn", "onResponse  : " + response.toString())
                    response.body()?.let {
                        emitter.onNext(it)
                        emitter.onComplete()
                    } ?: run {
                        emitter.onNext(RomanticComedy())
                        emitter.onComplete()
                    }
                }

                override fun onFailure(call: Call<RomanticComedy>, t: Throwable) {
                    Log.i("kdsjcn", "onFailure " + t.toString())
                    emitter.onError(t)
                }
            })
        }
    }
}