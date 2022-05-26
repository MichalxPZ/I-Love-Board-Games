package games_data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import data.local.GamesDataBase
import data.preferences.Preferences
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import data.remote.Api
import games_data.repository.GameRepositoryImpl
import pl.org.akai.game_list_domain.repository.GameRepository
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object GameRepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideGameRepository(
        api: Api,
        dataBase: GamesDataBase,
        preferences: Preferences
    ): GameRepository {
        return GameRepositoryImpl(
            api = api,
            preferences = preferences,
            db = dataBase
        )
    }

}