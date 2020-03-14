package com.zennymorh.bakingapp.ui.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zennymorh.bakingapp.model.Recipe
import com.zennymorh.bakingapp.remote.RecipeApi
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
            val listRecipe = RecipeApi.retrofitService.getRecipes()
            try {
                _recipes.value = listRecipe
            } catch (t: Throwable) {
                _status.value = "Failure: " + t.message

            }
        }

    }
}
