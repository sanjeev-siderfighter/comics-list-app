package com.siderfighter.comicsinfo.data.repository.rajcomics

import com.google.gson.annotations.SerializedName

/*
{"GoogleSheetData":[["First Name","Second Name","Mobile Number"],["Sanjeev","Kumar",6202649709],["Astha ","Jha","not gonna tell"]]}
 */
data class RajComicsResponse(
    @SerializedName("GoogleSheetData")
    val data: List<List<String>>
)
