package com.zennymorh.bakingapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
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

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private fun getRecipeList() {

        coroutineScope.launch {
            var getRecipesDeferred = RecipeApi.retrofitService.getRecipes()
            try {
                var listRecipe = getRecipesDeferred.await()
                _response.value = "We have ${listRecipe.size} recipes to work with"
            } catch (t: Throwable) {
                _response.value = "Failure: " + t.message

            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
