package com.test.abramovich.hearthstone.di

import android.content.Context
import com.test.abramovich.hearthstone.data.RepositoryImp
import com.test.abramovich.hearthstone.data.local.DataBase
import com.test.abramovich.hearthstone.data.local.FavoriteCardDao
import com.test.abramovich.hearthstone.data.remote.RemoteDataSource
import com.test.abramovich.hearthstone.data.remote.RemoteDataSourceImp
import com.test.abramovich.hearthstone.domain.Repository
import com.test.abramovich.hearthstone.presentation.naviagtion.Navigation
import com.test.abramovich.hearthstone.presentation.naviagtion.NavigationImp
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProviderFactory {

    @Provides
    fun providerFavoriteDao(@ApplicationContext context: Context): FavoriteCardDao =
        DataBase.getDatabase(context).favoriteCardDao()

}

@Module
@InstallIn(SingletonComponent::class)
interface BindFactory {

    @Binds
    fun providerRemoteDataSource(remoteDataSourceImp: RemoteDataSourceImp): RemoteDataSource

    @Singleton
    @Binds
    fun providerNavigation(navigationImp : NavigationImp): Navigation

      @Binds
      abstract fun bindRepository(repository: RepositoryImp): Repository
}