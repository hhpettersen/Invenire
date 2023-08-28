package no.app.invenire.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import no.app.invenire.datasource.RemoteDataSource
import no.app.invenire.datasource.RetrofitClientProvider

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {
    @Provides
    fun provideRemoteDataSource(): RemoteDataSource = RetrofitClientProvider.client
}