package com.aspis.tech.api

import com.aspis.tech.model.Url
import com.aspis.tech.model.UrlModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UrlRepository @Inject constructor(private val urlApi: UrlApi) {
    fun getUrls(): Flow<UrlModel> {
        return flow {
            emit(urlApi.getRecentUrls())
        }
    }
}