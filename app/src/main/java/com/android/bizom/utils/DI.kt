package com.android.bizom.utils

import android.content.Context
import com.android.bizom.BizomApplication
import com.android.bizom.data.file.FileApis
import com.android.bizom.data.file.FileApisImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@Suppress("unused")
@InstallIn(SingletonComponent::class)
class DI {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BizomApplication {
        return app as BizomApplication
    }

    @Provides
    @Singleton
    fun provideContext(application: BizomApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideFileApi(): FileApis {
        return FileApisImp()
    }


}