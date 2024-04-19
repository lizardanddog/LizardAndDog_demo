package com.lizardanddog.lizardanddog_demo.DataRetrievers

import com.android.volley.RequestQueue
import com.lizardanddog.lizardanddog_demo.Models.WikiModel
import com.lizardanddog.lizardanddog_demo.NetworkRequests.WikiDataCallback
import com.lizardanddog.lizardanddog_demo.NetworkRequests.WikipediaDataSource
import java.io.IOException


class WikiDataRetriever (private val wikipediaDataSource: WikipediaDataSource) {

    fun getDataFromWikipedia(searchTerm: String, offset: Int, wikiDataCallback: WikiDataCallback, requestQueue: RequestQueue) {
        return wikipediaDataSource.getDataFromWikipedia(searchTerm,offset,wikiDataCallback, requestQueue )
    }
}