package com.lizardanddog.lizardanddog_demo.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.volley.RequestQueue
import com.android.volley.VolleyError
import com.lizardanddog.lizardanddog_demo.DataRetrievers.WikiDataRetriever
import com.lizardanddog.lizardanddog_demo.Models.WikiModel
import com.lizardanddog.lizardanddog_demo.NetworkRequests.WikiDataCallback
import com.lizardanddog.lizardanddog_demo.NetworkRequests.WikipediaDataSource
import kotlinx.coroutines.launch

class WikiViewModel() : ViewModel() {

    private val _wikiModelsList = MutableLiveData<List<WikiModel>>()
    val wikiModelsList: LiveData<List<WikiModel>> = _wikiModelsList
    val wikiDataRetriever = WikiDataRetriever(WikipediaDataSource())
    // Retrieve a list of titles and thumbnails based on a specific search text.
    fun fetchData(searchTerm: String, requestQueue:RequestQueue) {
        viewModelScope.launch {
            wikiDataRetriever.getDataFromWikipedia(searchTerm,_wikiModelsList.value?.size ?: 0,object :WikiDataCallback{
                override fun onSuccess(data: List<WikiModel>) {
                    var currentList = _wikiModelsList.value ?: mutableListOf()
                    currentList = currentList + data
                    _wikiModelsList.postValue(currentList)
                }

                override fun onError(error: VolleyError) {
                  //TODO: Implement a proper error handling system...
                }

            }, requestQueue)

        }
    }

}