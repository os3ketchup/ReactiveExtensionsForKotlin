package com.example.reactiveextensionsforkotlin.models.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.reactiveextensionsforkotlin.models.Consumer
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface MyDao {
    @Insert
    fun addConsumer(consumer:Consumer): Single<Long>

    @Query("select  * from consumer ")
    fun getAllConsumer():Flowable<List<Consumer>>

}