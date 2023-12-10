package api.category

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import types.CategoryType
import java.io.IOException

class CategoryAPI  constructor(val token: String, val context: Context) {

    private val params = JSONObject()
    private val url = "http://192.168.31.220:8000/api/v1/categories/category"


    fun list(): ArrayList<CategoryType> {
        var categories = arrayListOf<CategoryType>()
        params.put("token", token)

        val queue = Volley.newRequestQueue(context)
        val request = JsonObjectRequest(Request.Method.GET, url, params, {response ->
            try {
                val gson = Gson()
                val itemType = object : TypeToken<ArrayList<CategoryType>>() {}.type
                val results = response.getJSONArray("results")
                categories = gson.fromJson(results.toString(), itemType)
                Log.d("api Response start", categories.toString())
            } catch (e: IOException) {
                Log.d("Response", e.message.toString())
            }
        }, { error ->
            Log.d("Response", error.message.toString())
        })

        request.setShouldCache(false)
        queue.add(request)
        return categories
    }
}