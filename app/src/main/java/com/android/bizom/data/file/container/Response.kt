package com.android.bizom.data.file.container

import com.google.gson.annotations.SerializedName

data class Response(val Result: String, @SerializedName("Reason") val reason: String, val Claims: List<Claim>)
