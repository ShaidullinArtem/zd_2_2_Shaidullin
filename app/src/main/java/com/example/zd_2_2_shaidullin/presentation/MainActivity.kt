/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.zd_2_2_shaidullin.presentation

import adapters.CategoryAdapter
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import api.category.CategoryAPI
import com.example.zd_2_2_shaidullin.R
import types.CategoryType

class MainActivity : ComponentActivity() {

    private lateinit var categoryRecyclerView: RecyclerView
    private lateinit var categoriesList: ArrayList<CategoryType>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", "")
        val api = CategoryAPI(token.toString(), this)
        val response = api.list()
        Log.d( "Component Response", response.toString())
        categoriesList = response


        categoryRecyclerView = findViewById(R.id.category_recycleView)
        categoryRecyclerView.layoutManager = LinearLayoutManager(this)
        categoryRecyclerView.setHasFixedSize(true)
        categoryRecyclerView.adapter = CategoryAdapter(categoriesList)
    }
}