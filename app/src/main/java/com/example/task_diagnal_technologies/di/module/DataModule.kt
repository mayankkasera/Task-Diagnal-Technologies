package com.example.task_diagnal_technologies.di.module

import com.example.task_diagnal_technologies.api.romantic_comedy.RomanticComedyRepository
import com.example.task_diagnal_technologies.api.romantic_comedy.RomanticComedyRepositoryI
import com.example.task_diagnal_technologies.api.romantic_comedy.RomanticComedyRequests
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [NetworkModule::class]
)
class DataModule {
    @Provides
    @Singleton
    fun provideRomanticComedyRepository(directionRequests: RomanticComedyRequests) : RomanticComedyRepositoryI {
        return RomanticComedyRepository(directionRequests)
    }
}