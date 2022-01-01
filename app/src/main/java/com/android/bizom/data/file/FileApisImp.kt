package com.android.bizom.data.file

import android.content.Context
import com.android.bizom.R
import com.android.bizom.data.file.container.Response
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.io.IOException


class FileApisImp : FileApis {

    override suspend fun sampleGet(appContext: Context): Response? {
        try {
            val jsonString: String = appContext.resources.openRawResource(R.raw.claims_json).bufferedReader().use { it.readText() }
            return Gson().fromJson(jsonString, Response::class.java)
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        return null
    }

}