package com.example.samplehiltapp.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.samplehiltapp.util.Resource.*
import com.example.samplehiltapp.util.Resource.Status.SUCCESS
import kotlinx.coroutines.Dispatchers

fun <T,A> performGetOperation(databaseQuery: () -> LiveData<T>,
                        networkCall: suspend () -> Resource<A>,
                        saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> {

    return liveData(Dispatchers.IO){
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if(responseStatus.status == SUCCESS){
            saveCallResult(responseStatus.data!!)

        }else if (responseStatus.status == Status.ERROR){
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }
}