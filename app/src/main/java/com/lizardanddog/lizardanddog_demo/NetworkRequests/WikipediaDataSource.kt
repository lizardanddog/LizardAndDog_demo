package com.lizardanddog.lizardanddog_demo.NetworkRequests

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.lizardanddog.lizardanddog_demo.Models.WikiModel
import org.json.JSONException
import org.json.JSONObject

class WikipediaDataSource {


    // Query the wikimedia API to get thumbnail image + page title based on a specific search term.
    val WIKIPEDIA_BASE_URL = "https://en.wikipedia.org/w/api.php"
    fun getDataFromWikipedia(searchTerm: String, offset: Int,  wikiDataCallback: WikiDataCallback, requestQueue: RequestQueue) {
        val url = buildUrl(searchTerm,offset)
        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val data = parseResponse(response)
                wikiDataCallback.onSuccess(data)
            },
            { error ->
                wikiDataCallback.onError(error)
            }
        )
        {
        }
        requestQueue.add(jsonObjectRequest)

    }

    private fun buildUrl(searchTerm: String, offset: Int): String {
        val builder = StringBuilder(WIKIPEDIA_BASE_URL)
        builder.append("?format=json")
            .append("&action=query")
            .append("&generator=search")
            .append("&gsrnamespace=0")
            .append("&gsrsearch=").append(searchTerm) //Search term used for the query
            .append("&gsrlimit=100") //Number of elements gotten
            .append("&gsroffset=").append(offset) //Offset used for subsequent queries
            .append("&prop=pageimages|extracts")
            .append("&pilimit=max")
            .append("&exintro")
            .append("&explaintext")
            .append("&exsentences=1")
            .append("&exlimit=max")
        return builder.toString()
    }
    private fun parseResponse(response: JSONObject): List<WikiModel> {

        var wikiModelListToAdd: List<WikiModel> = listOf()
        //Analyze response JSON
        try {
            val pages = response.getJSONObject("query").getJSONObject("pages")
            val keys = pages.keys()
            while (keys.hasNext()) {
                val key = keys.next()
                val page = pages.getJSONObject(key)
                val title = page.getString("title")
                val thumbnailUrl: String
                // Some pages have no thumbnails
                if (page.has("thumbnail")) {
                     thumbnailUrl = page.getJSONObject("thumbnail").getString("source")
                } else {
                   thumbnailUrl = ""
                }
             wikiModelListToAdd = wikiModelListToAdd + WikiModel(title,thumbnailUrl)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return wikiModelListToAdd
    }
}