package com.zennymorh.bakingapp.ui.ingredient

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zennymorh.bakingapp.model.Ingredient
import com.zennymorh.bakingapp.model.Recipe
import com.zennymorh.bakingapp.remote.RecipeApi
import kotlinx.coroutines.launch

class IngredientViewModel : ViewModel() {

    private val _status  = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val _ingredients = MutableLiveData<ArrayList<Ingredient>>()
    val ingredients: LiveData<ArrayList<Ingredient>>
        get() = _ingredients

    init {
        getIngredientsList()
    }

    private fun getIngredientsList() {
        viewModelScope.launch {
            val listIngredient = ArrayList<Ingredient>()
            try {
                _ingredients.value = listIngredient
                Log.i("dildo", listIngredient.toString())
            } catch (t: Throwable) {
                _status.value = "Failure" + t.message
            }
        }
    }
}
