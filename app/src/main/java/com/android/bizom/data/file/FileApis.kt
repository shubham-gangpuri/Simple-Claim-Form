package com.android.bizom.data.file

import android.content.Context
import com.android.bizom.data.file.container.Response
import dagger.hilt.android.qualifiers.ApplicationContext

interface FileApis {

    suspend fun sampleGet(@ApplicationContext appContext: Context): Response?

}