package com.aspis.tech.model


import com.google.gson.annotations.SerializedName

data class Blacklists(
    @SerializedName("spamhaus_dbl")
    val spamhausDbl: String,
    @SerializedName("surbl")
    val surbl: String
)