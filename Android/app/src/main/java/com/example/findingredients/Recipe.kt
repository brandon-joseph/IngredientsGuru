package com.example.findingredients

import java.io.Serializable


data class Recipe(
    var name: String,
    var ingredients: ArrayList<Ingredient>,
    val imageUrl: String,
    val url: String
): Serializable
