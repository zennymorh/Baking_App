package com.zennymorh.bakingapp

data class Recipe(
    val id: Int,
    val name: String,
    val ingredients: ArrayList<Ingredient>,
    val steps: ArrayList<Step>,
    val servings: Int,
    val image: String
)

data class Ingredient(
    val quantity: Double,
    val measure: String,
    val ingredient: String
)

data class Step(
    val id: Int,
    val shortDescription: String,
    val description: String,
    val videoURL: String,
    val thumbnail: String
)