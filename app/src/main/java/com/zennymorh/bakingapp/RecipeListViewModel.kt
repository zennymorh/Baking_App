package com.zennymorh.bakingapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeListViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        Log.d("random tag", "got here")
        getRecipeList()
    }

    private fun getRecipeList() {
        RecipeApi.retrofitService.getRecipes().enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("random tag", "i failed")
                _response.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d("random tag", "i passed")
                _response.value = response.body()
            }

        })

        Log.d("random tag", "got to the end of recipe list function")
    }
}
