package com.example.findingredients

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Ingredient (var id: Int, var name: String, val amount: Int): Parcelable
