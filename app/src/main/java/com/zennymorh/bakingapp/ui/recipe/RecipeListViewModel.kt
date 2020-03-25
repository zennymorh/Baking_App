package com.zennymorh.bakingapp.ui.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zennymorh.bakingapp.model.Recipe
import com.zennymorh.bakingapp.remote.RecipeApi
import kotlinx.coroutines.launch

enum class RecipeApiStatus{LOADING, ERROR, DONE}

class RecipeListViewModel : ViewModel() {

    private val _status  = MutableLiveData<RecipeApiStatus>()
    val status: LiveData<RecipeApiStatus>
        get() = _status

    private val _recipes = MutableLiveData<ArrayList<Recipe>>()
    val recipes: LiveData<ArrayList<Recipe>>
        get() = _recipes

    init {
        getRecipeList()
    }

    fun getRecipeList() {
        _status.value = RecipeApiStatus.LOADING
        viewModelScope.launch {

            try {
                val listRecipe = RecipeApi.retrofitService.getRecipes()
                _recipes.value = listRecipe
                _status.value = RecipeApiStatus.DONE

            } catch (t: Throwable) {
                _status.value = RecipeApiStatus.ERROR

            }
        }

    }
}
