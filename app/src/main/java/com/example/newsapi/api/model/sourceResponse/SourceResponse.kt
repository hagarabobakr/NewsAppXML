package com.example.newsapi.api.model.sourceResponse

import com.google.gson.annotations.SerializedName

data class SourceResponse(

    @field:SerializedName("sources")
	val sources: List<Source?>? = null,

    @field:SerializedName("status")
	val status: String? = null,

    @field:SerializedName("code")
    val code: String? = null,

    @field:SerializedName("message")
    val message: String? = null
)
