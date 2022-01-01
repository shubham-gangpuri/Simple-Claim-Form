package com.android.bizom.domain

import android.content.Context
import com.android.bizom.data.file.FileApis
import com.android.bizom.data.file.container.Response
import javax.inject.Inject

class SampleUseCase @Inject constructor(
    private val fileApis: FileApis,
    private val appContext: Context
) {
    suspend operator fun invoke(): Response? = fileApis.sampleGet(appContext)
}