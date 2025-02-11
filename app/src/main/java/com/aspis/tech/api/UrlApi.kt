package com.aspis.tech.api

import com.aspis.tech.model.UrlModel
import retrofit2.http.GET
import retrofit2.http.Path

interface UrlApi {
    @GET("/v1/urls/recent/limit/10")
    suspend fun getRecentUrls(): UrlModel
}