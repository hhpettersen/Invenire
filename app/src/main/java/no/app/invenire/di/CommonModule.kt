package no.app.invenire.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import no.app.invenire.database.AppDatabase
import no.app.invenire.datasource.RemoteDataSource
import no.app.invenire.datasource.RetrofitClientProvider
import no.app.invenire.repository.AdRepositoryImpl
import no.app.invenire.repository.implementation.AdRepository

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {
    @Provides
    fun provideRemoteDataSource(): RemoteDataSource = RetrofitClientProvider.client

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "ad_database"
    ).build()

    @Provides
    fun provideAdRepository(
        remoteDataSource: RemoteDataSource,
        appDatabase: AppDatabase,
    ): AdRepository = AdRepositoryImpl(remoteDataSource, appDatabase)
}
