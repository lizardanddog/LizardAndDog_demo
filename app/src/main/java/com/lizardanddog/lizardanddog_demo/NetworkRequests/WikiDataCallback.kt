package com.lizardanddog.lizardanddog_demo.NetworkRequests

import com.android.volley.VolleyError
import com.lizardanddog.lizardanddog_demo.Models.WikiModel

interface WikiDataCallback {
    fun onSuccess(data: List<WikiModel>)
    fun onError(error: VolleyError)
}