package com.zennymorh.bakingapp.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.zennymorh.bakingapp.model.Recipe
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://d17h27t6h515a5.cloudfront.net/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface RecipeApiService {
    @GET("topher/2017/May/59121517_baking/baking.json")
    suspend fun getRecipes():
            ArrayList<Recipe>
}

object RecipeApi {
    val retrofitService : RecipeApiService by lazy {
        retrofit.create(RecipeApiService::class.java)
    }
}