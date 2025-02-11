package com.aspis.tech.model


import com.google.gson.annotations.SerializedName

data class UrlModel(
    @SerializedName("query_status")
    val queryStatus: String,
    @SerializedName("urls")
    val urls: List<Url>
)