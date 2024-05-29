package com.codewithharsh.newsexpress.di

import android.app.Application
import com.codewithharsh.newsexpress.core.Constants.BASE_URL
import com.codewithharsh.newsexpress.data.manager.LocalUserManagerImpl
import com.codewithharsh.newsexpress.data.remote.NewsApi
import com.codewithharsh.newsexpress.data.repository.NewsRepositoryImpl
import com.codewithharsh.newsexpress.domain.manager.LocalUserManager
import com.codewithharsh.newsexpress.domain.repository.NewsRepository
import com.codewithharsh.newsexpress.domain.usecases.app_entry.AppEntryUseCases
import com.codewithharsh.newsexpress.domain.usecases.app_entry.ReadAppEntry
import com.codewithharsh.newsexpress.domain.usecases.app_entry.SaveAppEntry
import com.codewithharsh.newsexpress.domain.usecases.news.GetNews
import com.codewithharsh.newsexpress.domain.usecases.news.NewsUseCase
import com.codewithharsh.newsexpress.domain.usecases.news.SearchNews
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager {
        return LocalUserManagerImpl(application)
    }

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager): AppEntryUseCases {
        return AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager)
        )
    }

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository {
        return NewsRepositoryImpl(newsApi = newsApi)
    }

    @Provides
    @Singleton
    fun provideNewsUseCase(newsRepository: NewsRepository): NewsUseCase {
        return NewsUseCase(
            getNews = GetNews(newsRepository = newsRepository),
            searchNews = SearchNews(newsRepository = newsRepository)
        )
    }
}