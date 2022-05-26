package di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import data.local.GamesDao
import data.local.GamesDataBase
import data.remote.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    @ViewModelScoped
    fun provideGamesDb(@ApplicationContext context: Context): GamesDataBase {
        return Room.databaseBuilder(
            context,
            GamesDataBase::class.java,
            "games.db"
        ).build()
    }

    @Provides
    @ViewModelScoped
    fun provideGamesDao(
        gamesDataBase: GamesDataBase
    ): GamesDao {
        return gamesDataBase.dao
    }

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
}