package pl.org.akai.game_list_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import data.preferences.Preferences
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.org.akai.game_list_data.remote.Api
import pl.org.akai.game_list_data.repository.GameRepositoryImpl
import pl.org.akai.game_list_domain.repository.GameRepository
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object GameRepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideHttpClien(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply { level = HttpLoggingInterceptor.Level.BASIC }
            )
            .build()
    }

    @Provides
    @ViewModelScoped
    fun probideGamesApi(client: OkHttpClient): Api {
        return Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .client(client)
            .build()
            .create(Api::class.java)
    }

    @Provides
    @ViewModelScoped
    fun provideGameRepository(
        api: Api,
        preferences: Preferences
    ): GameRepository {
        return GameRepositoryImpl(
            api = api,
            preferences = preferences
        )
    }

}