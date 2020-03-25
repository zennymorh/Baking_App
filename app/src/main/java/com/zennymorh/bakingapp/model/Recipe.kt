package com.zennymorh.bakingapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//My data classes. Make sure the variable names are corresponding to the ones from the api.
@Parcelize
data class Recipe(
    val id: Int,
    val name: String,
    val ingredients: ArrayList<Ingredient>,
    val steps: ArrayList<Step>,
    val servings: Int,
    val image: String
) : Parcelable

@Parcelize
data class Ingredient(
    val quantity: Double,
    val measure: String,
    val ingredient: String
): Parcelable

@Parcelize
data class Step(
    val id: Int,
    val shortDescription: String,
    val description: String,
    val videoURL: String,
    val thumbnailURL: String
) : Parcelable