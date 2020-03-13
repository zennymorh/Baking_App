package com.zennymorh.bakingapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RecipeListViewModel : ViewModel() {

    private val _status  = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _status

    private val _recipes = MutableLiveData<ArrayList<Recipe>>()
    val recipes: LiveData<ArrayList<Recipe>>
        get() = _recipes

    init {
        getRecipeList()
    }

    private fun getRecipeList() {
        viewModelScope.launch {
            var listRecipe = RecipeApi.retrofitService.getRecipes()
            try {
                _recipes.value = listRecipe
            } catch (t: Throwable) {
                _status.value = "Failure: " + t.message

            }
        }

    }
}
